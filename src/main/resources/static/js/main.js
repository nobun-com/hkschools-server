//main.js

(function() {
	var app = angular.module('app', []);

	app.controller('kindergartenController', kindergartenController)
		.controller('primaryController', primaryController)
		.controller('secondaryController', secondaryController);
	
	function kindergartenController($http, $scope, $filter, $window, $location) {

		var district;
		
		if(document.getElementById('district')) {
			district = document.getElementById('district').value;
		}

		if(district) {
			$scope.schoolDistrict = district;
		}

		$scope.table;
		
		$scope.getAllData = function(api){
			$scope.loading = true;
			if($scope.table) {
				$scope.table.destroy();
			}

			$scope.table = $('#school-list').DataTable( {
		        "ajax": api,
		        "columns": [
		            { "data": "schoolName",
		            	"render":function(data, type, row, meta){
		            		if(type === 'display'){
		            			data = '<a href="/kindergarten-detail?schoolId='+row.id+'">'+data+'</a>'
		            		}
		            		return data;
		            	}
		            },
		            { "data": "district" },
		            { "data": "schoolCategory" },
		            { "data": "free" },
		            { "data": "withchildcareservicesforchildrenagedunder2" },
		            { "data": "totalNoOfPermitteAccommodationOfClassroomsinuse" },
		            { "data": "time" }
		        ],
		        "order": [[ 1, "desc" ]]
		    } );
			$scope.loading = false;
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
			$scope.getAllData(api);
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

		$scope.filterKindergartenData();
		$scope.getKGFilters();

	}

	function primaryController($http, $scope, $window, $location, $filter) {

		var district;
		
		if(document.getElementById('district')) {
			district = document.getElementById('district').value;
		}

		if(district) {
			$scope.schoolDistrict = district;
		}

		$scope.table;
		
		$scope.getAllPrimaryData = function(api){
			$scope.loading = true;
			if($scope.table) {
				$scope.table.destroy();
			}

			$scope.table = $('#school-list').DataTable( {
		        "ajax": api,
		        "columns": [
		            { "data": "schoolName",
		            	"render":function(data, type, row, meta){
		            		if(type === 'display'){
		            			data = '<a href="/primary-detail?schoolId='+row.id+'">'+data+'</a>'
		            		}
		            		return data;
		            	}
		            },
		            { "data": "schoolCategory" },
		            { "data": "religion" },
		            { "data": "district" },
		            { "data": "schoolId" },
		            { "data": "studentGender" }
		        ],
		        "order": [[ 3, "desc" ]]
		    } );
			$scope.loading = false;
		}

		$scope.filterPrimaryData  = function(){
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
			$scope.getAllPrimaryData(api);
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
		$scope.filterPrimaryData();
		$scope.getPSFilters();
		
	}

	function secondaryController($http, $scope, $window, $filter, $location) {

		var district;
		
		if(document.getElementById('district')) {
			district = document.getElementById('district').value;
		}

		if(district) {
			$scope.schoolDistrict = district;
		}

		$scope.loading = true;
		$scope.table;
		
		$scope.getAllSecondaryData = function(api){
			$scope.loading = true;
			if($scope.table) {
				$scope.table.destroy();
			}

			$scope.table = $('#school-list').DataTable( {
		        "ajax": api,
		        "columns": [
		            { "data": "schoolName",
		            	"render":function(data, type, row, meta){
		            		if(type === 'display'){
		            			data = '<a href="/secondary-detail?schoolId='+row.id+'">'+data+'</a>'
		            		}
		            		return data;
		            	}
		            },
		            { "data": "schoolType" },
		            { "data": "district" },
		            { "data": "sponsoringBody" },
		            { "data": "studentGender" },
		            { "data": "religion" },
		        ],
		        "order": [[ 1, "desc" ]]
		    } );
			$scope.loading = false;
		}

		$scope.filterSecondaryData = function() {
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
			$scope.getAllSecondaryData(api);
		}

		$scope.getSSFilters= function(){
			$http.get("/getSSFilters").then(function(response) {
				$scope.secondaryFilterData = response.data;
				$scope.studentGender ='all';
				$scope.religion='all';
				$scope.sponsoringBody='all';
				$scope.schoolCategory='all';
				$scope.schoolDistrict='all';
			},function(error) { })
		}

		$scope.filterSecondaryData();
		$scope.getSSFilters();
		
	}

})();