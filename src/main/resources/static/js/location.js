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
                var contentDiv = "<div class='panel panel-primary'>"
							      +"<div class='panel-heading'>" + school[2] + "</div>"
							      +"	<div class='panel-body'>"
							      +"		<div class='col-sm-6'>"
							      +"		 <img style='max-width:100px;' alt='图像不可用' src='" + school[4] + "'> </img>"
							      +"		</div>"
							      +"	<div class='col-sm-6'>" +school[3] +"</div>"
							      +"</div>"
							      +"</div>";
                var infoWindowOptions = {content: contentDiv};
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
				$scope.districts = $scope.allDistricts[$scope.selectedSchoolType];
				$scope.selectedDistrict = $scope.districts[0].district;
				$scope.getLocations($scope.selectedSchoolType, $scope.selectedDistrict);
			},function(error) { })
		}

		$scope.getDistricts();
		
		$scope.schoolTypeChanged = function(selectedSchoolType) {
			$scope.selectedSchoolType = selectedSchoolType;
			$scope.districts = $scope.allDistricts[selectedSchoolType];
			$scope.selectedDistrict = $scope.districts[0].district;
			$scope.getLocations($scope.selectedSchoolType, $scope.selectedDistrict);
		}
		
		$scope.districtChanged = function(selectedDistrict) {
			$scope.selectedDistrict = selectedDistrict;
			$scope.getLocations($scope.selectedSchoolType, $scope.selectedDistrict);
		}
		
		$scope.view = function() {
			if($scope.viewFlag) {
				$('#col1').removeClass('col-sm-8')
				$('#col1').addClass('col-sm-12');
				$('#col2').hide(1000);
			} else {
				$('#col1').removeClass('col-sm-12')
				$('#col1').addClass('col-sm-8');
				$('#col2').show(1000);
			}
			$scope.viewFlag = !$scope.viewFlag;
		}
		
		$scope.panTo = function(school) {
			var latLng = new google.maps.LatLng(school[0], school[1]);
			rootMap.panTo(latLng);
		}

	}

})();