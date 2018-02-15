//main.js

(function() {
	var app = angular.module('app', [ 'ngGrid', 'ngTouch', 'ui.grid','ui.grid.pagination' ]);

	app.controller('kindergartenController', kindergartenController)
			.controller('primaryController', primaryController).controller(
					'secondaryController', secondaryController);
	
	function kindergartenController($http, $scope, $filter, $window, $location) {
			$scope.loading = true;	
			$scope.gridOptions = {
			showFooter : true,
			 rowTemplate: '<div ng-click="grid.appScope.getKinderGartenSchoolRowData(row)" ng-repeat="(colRenderIndex, col) in colContainer.renderedColumns track by col.uid" class="ui-grid-cell" ng-class="col.colIndex()" ui-grid-cell></div>',
			paginationPageSizes : [ 10, 25, 50, 100 ],
			paginationPageSize : 10,
			rowHeight: 52,
			columnDefs : [ {
				 name : 'schoolName',
				 displayName:'幼校名稱'
				 }, {
				 name : 'district',
				 displayName : '地區'
				 }, {
				 name : 'schoolCategory',
				 displayName : '類別'
				 }, {
				 name : 'applicationFee',
				 displayName : '免費'
				 }, {
				 name : 'withchildcareservicesforchildrenagedunder2',
				 displayName : '幼兒'
				 }, {	 
				 name : 'scale',
				 displayName : '规模'
				 }, {
				 name : 'time',
				 displayName:'時間'
				 }, ]
		};
		
		$scope.refreshData = function() {
			$scope.gridOptions.data = $filter('filter')($scope.searchData, $scope.searchText);
		};

		$scope.getAllData = function(){
			$http.get("/getAllKinderGarten/").then(function(response) {
				$scope.searchData = response.data;
				$scope.gridOptions.data = response.data;
				$scope.loading = false;
			},function(error) {
				
			});
		}

		$scope.getKinderGartenSchoolRowData = function(row){
			$scope.KinderGartenSchoolRowData=row.entity;
			$window.location.href ='/kinderGarten-detail?schoolId='+row.entity.id;
			
		}
		
		$scope.filterKindergartenData = function(){
			$scope.loading = true;
			var api = '/getAllKinderGarten?';
			
			if($scope.schoolCategoury && $scope.schoolCategoury!="all") {
				api = api + "&schoolCategoury=" + $scope.schoolCategoury;	 
			}
			
			if($scope.schoolDistrict && $scope.schoolDistrict!="all") {
				api = api + "&schoolDistrict=" + $scope.schoolDistrict;
			}
			
			if($scope.withchildcareservicesforchildrenagedunder2 && $scope.withchildcareservicesforchildrenagedunder2!="all") {
				api = api + "&withchildcareservicesforchildrenagedunder2=" + $scope.withchildcareservicesforchildrenagedunder2;
			}
			if($scope.time && $scope.time!="all"){
				api = api + "&time=" + $scope.time;
			}
			$http.get(api)
			.then(function(response){
				$scope.loading = false;
			$scope.searchData = response.Data;
			$scope.gridOptions.data = response.data;
			},function(error){
					
			});
		}
		
		$scope.getKGFilters= function(){
			$http.get("/getKGFilters").then(function(response) {
				$scope.filterData = response.data;
				$scope.schoolCategoury='all';
				$scope.schoolDistrict='all';
				$scope.withchildcareservicesforchildrenagedunder2='all';
				$scope.time='all';
			},function(error) {
				
			});
		}
		$scope.getKGFilters();

		var district = document.getElementById('district').value;
		if(district) {
			$scope.schoolDistrict = district;
			$scope.filterKindergartenData();
		} else {
			$scope.getAllData();
		}
		
		$scope.getRowIndex = function(row) {
			var element = '<a id="myCheck" ng-href="/kindergarten-detail"></a>';
			console.log(document.getElementById("myCheck"));// .click();
		}

	}

	function primaryController($http, $scope, $window, $location, $filter) {

		$scope.loading = true;
		$scope.gridOptions = {
			showFooter : true,
			paginationPageSizes : [10, 25, 50, 100 ],
			paginationPageSize : 10,
			rowHeight: 52,
			 rowTemplate: '<div ng-click="grid.appScope.getPrimarySchoolRowData(row)" ng-repeat="(colRenderIndex, col) in colContainer.renderedColumns track by col.uid" class="ui-grid-cell" ng-class="col.colIndex()" ui-grid-cell></div>',
			columnDefs : [ {
				name : 'schoolName',
				displayName : '幼校名稱'	
			}, {
				name : 'address',
				displayName : '地址'
			}, {
				name : 'schoolCategory',
				displayName : '類別'
			}, {
				name : 'religion',
				displayName : '宗教'
			}, {
				 name : 'district',
				 displayName : '地區'
			 }, {
				name : 'schoolId',
				displayName : '学校编号'
			}, {
				name : 'studentGender',
				displayName : '学生性别'
			}, ]
		};

		$scope.refreshData = function() {
			$scope.gridOptions.data = $filter('filter')($scope.searchData, $scope.searchText);
		};
		$scope.getAllPrimaryData = function(){
			$http.get("/getAllPrimary/").then(function(response) {
				$scope.searchData = response.data;
				$scope.gridOptions.data = response.data;
				$scope.loading = false;
			},function(error) {
				
			});
		}
		
		$scope.getPrimarySchoolRowData = function(row){
			$scope.primarySchoolRowData=row.entity;
			$window.location.href ='/primary-detail?schoolId='+row.entity.id;
		}
		
		$scope.filterPrimaryData  = function(){
			$scope.loading = true;
			var api = '/getAllPrimarySchool?';
			
			if ($scope.schoolCategory && $scope.schoolCategory!="all") {
				api = api + "&schoolCategory=" + $scope.schoolCategory;
			}
			
			if ($scope.schoolDistrict && $scope.schoolDistrict!="all") {
				api = api + "&schoolDistrict=" + $scope.schoolDistrict;
			}
			
			if ($scope.religion && $scope.religion!="all") {
				api = api + "&religion=" + $scope.religion;
			}
			
			if ($scope.studentGender && $scope.studentGender!="all") {
				api = api + "&studentGender=" + $scope.studentGender;
			}
			
			if($scope.schoolId && $scope.schoolId!="all") {
				 api = api + "&schoolId="+ $scope.schoolId;
			 }
				
			$http.get(api)
			.then(function(response){
				$scope.loading = false;
			$scope.searchData = response.Data;
			$scope.gridOptions.data = response.data;
		},function(error) {
			
			});
		}
		
		$scope.getPSFilters= function(){
			$http.get("/getPSFilters").then(function(response) {
				$scope.primaryFilterData = response.data;
				$scope.schoolCategory='all';
				$scope.schoolDistrict='all';
				$scope.religion='all';
				$scope.studentGender='all';
				$scope.schoolId='all';
			},function(error) {
				
			});
		}
		$scope.getPSFilters();
		
		var district = document.getElementById('district').value;
		if(district) {
			$scope.schoolDistrict = district;
			$scope.filterPrimaryData();
		} else {
			$scope.getAllPrimaryData();
		}
	}

	function secondaryController($http, $scope, $window, $filter, $location) {

		$scope.loading =true;
		$scope.gridOptions = {
			showFooter : true,
			paginationPageSizes : [ 10, 25, 50, 100 ],
			paginationPageSize : 10,
			rowHeight: 52,
			rowTemplate: '<div ng-click="grid.appScope.getSecondarySchoolRowData(row)" ng-repeat="(colRenderIndex, col) in colContainer.renderedColumns track by col.uid" class="ui-grid-cell" ng-class="col.colIndex()" ui-grid-cell></div>',
			columnDefs : [ {
				name : 'schoolName',
				displayName: '幼校名稱'
			}, {
				name : 'district',
				displayName : '地區'
			}, {
				name : 'schoolType',
				displayName : '類別'
			}, {
				name : 'sponsoringBody',
				displayName : '赞助机构'
			}, {
				name : 'religion',
				displayName : '宗教'
			}, {
				name : 'studentGender',
				displayName:'学生性别'
			}, ]
		};

		$scope.refreshData = function() {
			$scope.gridOptions.data = $filter('filter')($scope.searchData, $scope.searchText);
		}

		
		$scope.getAllSecondaryData = function(){
			$http.get("/getAllsecondary/").then(function(response) {
				$scope.searchData = response.data;
				$scope.gridOptions.data = response.data;
				$scope.loading = false;
			},function(error) {
				
			});
			
		}
			$scope.getSecondarySchoolRowData = function(row){
				$window.location.href ='/secondary-detail?schoolId='+row.entity.id;
			}
		
		$scope.filterSecondaryData = function() {
			$scope.loading = true;
			var api = '/getAllSecondarySchool?';
			
			if($scope.schoolDistrict && $scope.schoolDistrict!="all") {
				 api = api + "&schoolDistrict="+$scope.schoolDistrict;
			 }
			
			if($scope.sponsoringBody && $scope.sponsoringBody!="all") {
				api = api + "&sponsoringBody=" + $scope.sponsoringBody;
			}
			
			if($scope.religion && $scope.religion!="all") {
				api = api + "&religion=" + $scope.religion;
			}
			
			if($scope.studentGender && $scope.studentGender!="all") {
				api = api + "&studentGender=" + $scope.studentGender;
			}
		
			if($scope.schoolCategory && $scope.schoolCategory!="all") {
				api = api + "&schoolCategory=" + $scope.schoolCategory;
			}
			
			$http.get(api)
			.then(function(response) {
				$scope.searchData = response.Data;
				$scope.gridOptions.data = response.data;
				$scope.loading = false;
			},function(error) {
				
			});
		}
		$scope.getSSFilters= function(){
			$http.get("/getSSFilters").then(function(response) {
				$scope.secondaryFilterData = response.data;
				$scope.studentGender ='all';
				$scope.religion='all';
				$scope.sponsoringBody='all';
				$scope.schoolCategory='all';
				$scope.schoolDistrict='all';
			},function(error) {
				
			})
		}
		$scope.getSSFilters();
		
		var district = document.getElementById('district').value;
		if(district) {
			$scope.schoolDistrict = district;
			$scope.filterSecondaryData();
		} else {
			$scope.getAllSecondaryData();
		}

		}

})();