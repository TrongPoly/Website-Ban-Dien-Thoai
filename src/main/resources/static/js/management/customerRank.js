let host = "http://localhost:8080/admin/rest";
const app = angular.module("AdminHangApp", []);
app.controller("AdminHangCtrl", function($scope, $http) {
	$scope.form = {};
	$scope.items = [];
	
	
	   $scope.reset = function(){   
		    $scope.form = {};
        $scope.key = null;
    }

	$scope.load_all = function() {
		var url = `${host}/hangkhachhang`;
		$http.get(url).then(resp => {
			$scope.items = resp.data;
			console.log("Succes", resp);
		}).catch((error) => {
			console.log("Error", error);
		});
	}
	$scope.edit = function(id){
		var url = `${host}/hangkhachhang/${id}`;
		$http.get(url).then(resp => {
			/*window.location.href = "customerRank";*/
			$scope.form = resp.data; 
			
			console.log("Success", resp);
		}).catch((error) => {
			console.log("Error",error);
		})
	}
	   $scope.create = function(){
        var item = angular.copy($scope.form);
        var url = `${host}/hangkhachhang`;
        $http.post(url, item).then(resp => {	
			
            $scope.items.push(item);
             alert("thêm thành công!")	 
            $scope.reset();
           
          
/*             window.location.href = "customerRankTable";
*/           
        }).catch((error) => {
			alert("thêm thất bại!")
            console.log("Error", error);
        });
    }
	
	$scope.update = function(){
		var item = angular.copy($scope.form);
		var url = `${host}/hangkhachhang/${$scope.form.id}`;
		$http
				.put(url, item).then(resp => {
					var index = $scope.items.findIndex(item =>
						item.id == $scope.form.id);
					alert("cập nhật thành công!")	
					$scope.items[index] = resp.data;
					
					console.log("Succes", resp);
				}).catch((error) =>{
					alert("cập nhật thất bại!")
					console.log("Error", error)
				});
					
				
	}
	
	$scope.delete = function(id){
		var url = `${host}/hangkhachhang/${id}`;
		$http.delete(url).then(resp => {
			var index = $scope.items.findIndex(item => item.id == id);
		
			$scope.items.splice(index, 1);
			alert("xóa thành công")
			$scope.reset();
			console.log("Succes", resp);
		}).catch((error) => {
			alert("xóa không thành công")
			console.log("Error", error)
		});
	}
	
	$scope.load_all();
	$scope.reset();

	
	})