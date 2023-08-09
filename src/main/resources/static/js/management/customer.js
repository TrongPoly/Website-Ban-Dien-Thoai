let host = "http://localhost:8080/rest";
const app = angular.module("AdminKhApp", []);
app.controller("AdminKhCtrl", function($scope, $http) {
	$scope.form = {};
	$scope.items = [];
	$scope.hkhs = [];
	
	
	   $scope.reset = function(){
        $scope.form = {trangThai: true };
        $scope.key = null;
    }
	
	$scope.load_all_hkh = function() {
		var url = `${host}/hangkhachhang`;
		$http.get(url).then(resp => {
			$scope.hkhs = resp.data;
			console.log("Succes", resp);
		}).catch((error) => {
			console.log("Error", error);
		});
	}
	
	$scope.load_all = function() {
		var url = `${host}/khachhang`;
		$http.get(url).then(resp => {
			$scope.items = resp.data;
			console.log("Succes", resp);
		}).catch((error) => {
			console.log("Error", error);
		});
	}
	$scope.edit = function(id){
		var url = `${host}/khachhang/${id}`;
		$http.get(url).then(resp => {
			$scope.form = resp.data;
			
			console.log("Success", resp);
		}).catch((error) => {
			console.log("Error",error);
		})
	}
	   $scope.create = function(){
        var item = angular.copy($scope.form);
        var url = `${host}/khachhang`;
        $http.post(url, item).then(resp => {	
			
            $scope.items.push(item);
             alert("thêm thành công!")	 
            $scope.reset();
           
            console.log("Success", resp);
         	 if (item.soluongton == 0) {
                item.trangthai = false;
            }
            location.reload();
        }).catch((error) => {
			alert("thêm thất bại!")
            console.log("Error", error);
        });
    }
	
	$scope.update = function(){
		var item = angular.copy($scope.form);
		var url = `${host}/khachhang/${$scope.form.id}`;
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
		var url = `${host}/khachhang/${id}`;
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
	
	$scope.pager = {
		page: 0,
		size: 5,
		get items(){
			var start = this.page * this.size;
			return $scope.items.slice(start,start + this.size);
		},
		
		get count(){
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		first(){
			this.page = 0;
		},
		prev(){
			this.page--;
			if(this.page< 0){
				this.last();
			}
		},
		next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			}
		},
		last(){
			this.page = this.count - 1;
		}
	
	}
	
	$scope.load_all_hkh();
	$scope.load_all();
	$scope.reset();
})