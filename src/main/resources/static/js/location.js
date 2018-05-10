(function() {
	var app = angular.module('app', []);

	app.controller('mapController', mapController);
	
	function mapController($http, $scope, $window, $filter, $location) {

        var infoWindow, rootMap;
        $scope.viewFlag = true;

        function reCenter(position) {
	        var options = {
	            center: position,
	            zoom: 15,
	            zoomControl: true,
	            scaleControl: true
	        }
	        rootMap = new google.maps.Map(document.getElementById("map"), options);
        }

        function contentDiv(school) {
            var contentDiv = 
				"<div>"
					+"<div class='marker-card-header'>" + school[2] + "</div>"
					+"<div>"
						+"	<div class='col-sm-4'>"
						+"		<img style='max-width:100px;' class='img-thumbnail' alt='图像不可用' src='" + school[4] + "'> </img>"
						+"	</div>"
						+"	<div class='col-sm-8'>"
						+"	<table>"
						+"		<tbody>"
						+"         <tr>"
						+"            <th>地址 : </th><!-- Address -->"
						+"            <td>" + school[3] + "</td>"
						+"         </tr>"
						+"         <tr>"
						+"            <th>聯繫 : </th><!-- Tel -->"
						+"            <td>" + school[5] + "</td>"
						+"         </tr>"
						+"         <tr>"
						+"            <th>傳真 : </th><!-- Fax -->"
						+"            <td>" + school[6] + "</td>"
						+"         </tr>"
						+"         <tr>"
						+"            <th>網址 : </th><!-- Website -->"
						+"            <td><a ng:href='" + school[7] + "' target='_blank'>" + school[7] + "</a></td>"
						+"         </tr>"
						+"      </tbody>"
						+"  </table>"
						+"</div>"
					+"</div>"
				+"</div>";
            return contentDiv;
        }
        
        function setMarker(map, school, index) {
        	var position = new google.maps.LatLng(school[0], school[1]);
            var marker;
            var markerOptions = {
                position: position,
                map: map,
                title: school[2],
                icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=' + index + '|FE6256|000000',
	            animation: google.maps.Animation.DROP
            };

            marker = new google.maps.Marker(markerOptions);

            google.maps.event.addListener(marker, 'click', function () {
                if (infoWindow !== void 0) {
                    infoWindow.close();
                }
                var infoWindowOptions = {content: contentDiv(school)};
                infoWindow = new google.maps.InfoWindow(infoWindowOptions);
                infoWindow.open(map, marker);
            });
        }

		$scope.getLocations= function(schoolType, district){
			var api = "/getLocations?schoolType=" + schoolType + "&district=" + district;
			$http.get(api).then(function(response) {
				$scope.schools = response.data;
				reCenter(new google.maps.LatLng($scope.schools[0][0], $scope.schools[0][1]));
				for(var index = 0; index < $scope.schools.length; index++) {
					var school = $scope.schools[index];
			        setMarker(rootMap, school, index+1);
				}
			},function(error) { })
		}

		$scope.getDistricts= function(){
			$scope.selectedSchoolType = 'kindergarten_school';
			$http.get("/getDistricts").then(function(response) {
				$scope.allDistricts = response.data;
				$scope.selectedDistrict = $scope.allDistricts[$scope.selectedSchoolType][0].district;
				$scope.districts = $scope.allDistricts[$scope.selectedSchoolType];
				$scope.getLocations($scope.selectedSchoolType, $scope.selectedDistrict);
			},function(error) { })
		}

		$scope.getDistricts();
		
		$scope.schoolTypeChanged = function(selectedSchoolType) {
			$scope.selectedSchoolType = selectedSchoolType;
			$scope.selectedDistrict = $scope.allDistricts[$scope.selectedSchoolType][0].district;
			$scope.districts = $scope.allDistricts[selectedSchoolType];
			$scope.getLocations($scope.selectedSchoolType, $scope.selectedDistrict);
		}
		
		$scope.districtChanged = function(selectedDistrict) {
			$scope.selectedDistrict = selectedDistrict;
			$scope.getLocations($scope.selectedSchoolType, $scope.selectedDistrict);
		}
		
		$scope.view = function() {
			if($scope.viewFlag) {
				$('#col1').removeClass('col-sm-8');
				$('#col1').addClass('col-sm-12');
				$('#col2').hide(1000);
			} else {
				$('#col1').removeClass('col-sm-12');
				$('#col1').addClass('col-sm-8');
				$('#col2').show(1000);
			}
			$scope.viewFlag = !$scope.viewFlag;
		}
		
		$scope.panTo = function(school) {
			var latLng = new google.maps.LatLng(school[0], school[1]);
			//rootMap.panTo(latLng);
		}

		$scope.isSelectedDistrict = function(district) {
			return $scope.selectedDistrict == district;
		}

	}

})();