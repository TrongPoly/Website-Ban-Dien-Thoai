let host = "http://localhost:8080/rest";
const app = angular.module("AdminSpApp", []);
app.controller("AdminSpCtrl", function($scope, $http) {
	$scope.form = {};
	$scope.items = [];
	$scope.nhasanxuats = [];



	$scope.reset = function() {
		$scope.form = { trangThai: true };
		$scope.key = null;
	}

	$scope.load_all_nsx = function() {
		var url = `${host}/nhasanxuat`;
		$http.get(url).then(resp => {
			$scope.nhasanxuats = resp.data;
			console.log("Succes", resp);
		}).catch((error) => {
			console.log("Error", error);
		});
	}

	$scope.load_all = function() {
		var url = `${host}/sanpham`;
		$http.get(url).then(resp => {
			$scope.items = resp.data;
			console.log("Succes", resp);
		}).catch((error) => {
			console.log("Error", error);
		});
	}
	$scope.edit = function(id) {
		var url = `${host}/sanpham/${id}`;
		$http.get(url).then(resp => {
			$scope.form = resp.data;

			console.log("Success", resp);
		}).catch((error) => {
			console.log("Error", error);
		})
	}
	$scope.create = function() {
		var item = angular.copy($scope.form);
		var url = `${host}/sanpham`;
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

	$scope.update = function() {
		var item = angular.copy($scope.form);
		var nsx = angular.copy($scope.form.nhaSanXuat.id);
		var url = `${host}/sanpham/${$scope.form.id}?nhaSX=${nsx}`;

		$http
			.put(url, item).then(resp => {
				var index = $scope.items.findIndex(item =>
					item.id == $scope.form.id);

				alert("cập nhật thành công!")
				$scope.items[index] = resp.data;

				console.log("Succes", resp);
			}).catch((error) => {
				alert("cập nhật thất bại!")
				console.log("Error", error)
			});
		/*			
		 $scope.search = function() {
		  var name = $scope.name;
		  $http.get(`'${host}/sanpham/search/' + $scope.pageNumber + '/' + $scope.pageSize + '/' + name`)
			.success(function(data) {
			  $scope.items = data.sanpham;
			});
		};			
				*/




	}

	$scope.delete = function(id) {
		var url = `${host}/sanpham/${id}`;
		if (confirm("Bạn có chắc muốn xóa sản phẩm này?")) {
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
	}


	//upload image
	$scope.imageChanged = function(files) {
		var data = new FormData();

		data.append('file', files[0]);
		$http.post('/rest/upload/images', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.anhSanPham = resp.data.name
		}).catch(error => {
			alert("lỗi upload hình ảnh");
			console.log("Error", error);
		})
	}
	$scope.pager = {
		page: 0,
		size: 5,

		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},

		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}

	};
	$scope.load_all_nsx();
	$scope.load_all();
	$scope.reset();


});
