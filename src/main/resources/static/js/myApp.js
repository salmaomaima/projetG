var myApp=angular.module("myApp",[]);


myApp.controller("listetudiantcontroller",function($scope,$http){
	$scope.pageetudiant=null;
	$scope.pagecourant=0;
	$scope.size=5;
	
	
	$scope.listetudiant=function(){
		$http.get("etudiants?page="+$scope.pagecourant+"&size="+$scope.size)
		.success(function(data){
			$scope.pageetudiant=data;
		})
			};
			
			$scope.listetudiant();
});


myApp.controller("index",function($http,$scope){
	$scope.menu=["inscription","listes","utilisateur","logout"];
	$scope.selecttedmenu=null;
	$scope.select=function(m){
		$scope.selectedmenu=m;
	};
});











myApp.controller("InscriptionController", function($scope,$http){
	 
	$scope.etudiant={};
	$scope.errors=null;
	$scope.mode={value:"form"};
	$scope.exception={message:null};
	
	$scope.saveetudiant=function(){
		$http.post("Setudiant",$scope.etudiant)
		.success(function(data){
			if(!data.errors){
				$scope.etudiant=data;
				$scope.errors=null;
				$scope.mode.value="confirm";
			}
			else{
				$scope.errors=data;
			}
		})
		.error(function(data){
			$scope.exception.message=data.message;
		})
		;
	};
	

	
});