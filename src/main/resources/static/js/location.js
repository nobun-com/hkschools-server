(function() {
	var app = angular.module('app', []);

	app.controller('mapController', mapController);
	
	function mapController($http, $scope, $window, $filter, $location) {

        var infoWindow, rootMap;
        var cardsOnPage = 10;
        var activePageIndex = 0;
        var markers = [];
        var viewFlag = true;

        var options = {
            center: new google.maps.LatLng(22.3964, 114.1095),
            zoom: 15,
            zoomControl: true,
            scaleControl: true,
            streetViewControl: false,
            fullscreenControl: false,
        }
        rootMap = new google.maps.Map(document.getElementById("map"), options);

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
						+"            <td><a href='" + school[7] + "' target='_blank'>" + school[7] + "</a></td>"
						+"         </tr>"
						+"         <tr>"
						+"            <td colspan='2'><a href='/" + $scope.selectedSchoolType + "-detail?schoolId=" + school[8] + "' >更多信息</a></td>"
						+"         </tr>"
						+"      </tbody>"
						+"  </table>"
						+"</div>"
					+"</div>"
				+"</div>";
            return contentDiv;
        }
        
        function setMarker(school, index) {
        	var position = new google.maps.LatLng(school[0], school[1]);
            var markerOptions = {
                position: position,
                map: rootMap,
                title: school[2],
                icon: 'http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=' + index + '|FE6256|000000',
	            animation: google.maps.Animation.DROP
            };

            var marker = new google.maps.Marker(markerOptions);

            markers.push(marker);

            google.maps.event.addListener(marker, 'click', function () {
                if (infoWindow !== void 0) {
                    infoWindow.close();
                }
                var infoWindowOptions = {content: contentDiv(school)};
                infoWindow = new google.maps.InfoWindow(infoWindowOptions);
                infoWindow.open(rootMap, marker);
            });
        }

        function refreshMap(schools) {
        	$scope.schoolsToDisplay = schools;
			for(var index = 0; index < markers.length; index++) {
				markers[index].setMap(null);
			}
			markers = [];
			$scope.panTo(schools[0]);
			for(var index = 0; index < schools.length; index++) {
				var school = schools[index];
		        setMarker(school, index+1);
			}
        }

		$scope.getLocations= function(schoolType, district){
			var api = "/getLocations?schoolType=" + schoolType + "&district=" + district;
			$http.get(api).then(function(response) {
				$scope.schools = response.data;
				var btnsSize = ($scope.schools.length % cardsOnPage == 0) ? ($scope.schools.length / cardsOnPage) : (parseInt($scope.schools.length / cardsOnPage) + 1);
				$scope.btns = new Array(btnsSize);
				$scope.showPage(0);
			},function(error) { })
		}

		$scope.getDistricts= function(){
			$scope.selectedSchoolType = 'kindergarten';
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
			if(viewFlag) {
				$('#col1').removeClass('col-sm-8');
				$('#col1').addClass('col-sm-12');
				$('#view-btn').removeClass('fa-expand');
				$('#view-btn').addClass('fa-compress');
				$('#col2').hide(1000);
			} else {
				$('#col1').removeClass('col-sm-12');
				$('#col1').addClass('col-sm-8');
				$('#col2').show(1000);
				$('#view-btn').addClass('fa-expand');
				$('#view-btn').removeClass('fa-compress');
			}
			viewFlag = !viewFlag;
		}
		
		$scope.panTo = function(school) {
			rootMap.panTo(new google.maps.LatLng(school[0], school[1]));
		}

		$scope.isSelectedDistrict = function(district) {
			return $scope.selectedDistrict == district;
		}
		
		$scope.showPage = function(pageIndex) {
			activePageIndex = pageIndex;
			refreshMap($scope.schools.slice((pageIndex * cardsOnPage), (pageIndex * cardsOnPage) + cardsOnPage));
		}
		
		$scope.isActivePage = function(pageIndex) {
			return activePageIndex == pageIndex;
		}

	}

})();