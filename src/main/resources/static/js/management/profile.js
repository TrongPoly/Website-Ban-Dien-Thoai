let host = "http://localhost:8080/rest";
const app = angular.module("AdminPfApp", []);
app.controller("AdminPfCtrl", function($scope, $http) {
	$scope.form = {};
	$scope.diaChi = [];
	$scope.editProfile = false;
	$scope.editAddress = false;


	$scope.reset = function() {
		$scope.form = {};
		$scope.key = null;
	}



	$scope.load_all = function() {
		var url = `${host}/profile/getUser`;
		$scope.khachHang={};
		$http.get(url).then(resp => {
			$scope.khachHang = resp.data;
			console.log("Succes", resp);
		}).catch((error) => {
			console.log("Error", error);
		});
	}

	$scope.load_all_dc = function() {
		var url = `${host}/address/getAll`;
		$http.get(url).then(resp => {
			$scope.diaChi = resp.data;
			console.log("Succes", resp);
		}).catch((error) => {
			console.log("Error", error);
		});
	}

	$scope.load_tinh = function() {
		var url = `${host}/address/getProvince`;
		$scope.tinh = [];
		$http
			.get(url)
			.then((resp) => {
				$scope.tinh = resp.data;

			})
			.catch((error) => {
				alert(error.status)
			});
	}
	$scope.load_quan = function() {
		let idTinh = $scope.form.tinh;
		var url = `${host}/address/getDistrict/${idTinh}`;
		$scope.quan = [];
		$http
			.get(url)
			.then((resp) => {
				$scope.quan = resp.data;
			})
			.catch((error) => {
				alert(error.status)
			});
	}
	$scope.load_phuong = function() {
		let idQuan = $scope.form.quan;
		var url = `${host}/address/getWards/${idQuan}`;
		$scope.phuong = [];
		$http
			.get(url)
			.then((resp) => {
				$scope.phuong = resp.data;
			})
			.catch((error) => {
				alert(error.status)
			});
	}

	$scope.create_address = function() {
		var url = `${host}/address/create`; // Sử dụng dấu nháy kép để bao quanh biểu thức ${host}
		var data = angular.copy($scope.form);

		$http
			.post(url, data)
			.then((resp) => {
				$scope.editAddress = false;
				$scope.load_all_dc();
				alert("Thêm địa chỉ thành công");
			})
			.catch((error) => {
				alert(error.status)
			});


	};

	$scope.edit = function(id) {
		$scope.editProfile = !id;
	}
	$scope.create = function() {
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

	$scope.editADR = function(id) {
		$scope.editAddress=!id;
	};

	$scope.update = function() {

		//Lỗi bỏ trống tên khách hàng
		if (!$scope.khachHang.tenKhachHang) {
			alert("Vui lòng nhập tên khách hàng!!")

			return;
		}
		//Lỗi bỏ trống số điện thoại 
		if (!$scope.khachHang.soDienThoai) {
			alert("Vui lòng nhập tên khách hàng!!")
			$scope.errorMessage = "Vui lòng nhập tên sản phẩm!!";
			$('#errorModal').modal('show'); // Show the modal
			return;
		}
		//Lỗi sai cú pháp số điện thoại 
		if (isNaN($scope.khachHang.soDienThoai) || $scope.khachHang.soDienThoai.length < 10 || $scope.khachHang.soDienThoai.length > 11) {
			alert("Số điện thoại không hợp lệ!");
			return;
		}


		var item = angular.copy($scope.khachHang);
		var url = `${host}/profile/${$scope.khachHang.id}`;
		$http
			.put(url, item).then(resp => {
				alert("cập nhật thành công!")
				$scope.editProfile = false;
				console.log("Succes", resp);
			}).catch((error) => {
				alert(error.status)
				console.log("Error", error)
			});


	}

	$scope.delete = function(id) {
		var url = `${host}/profile/${id}`;
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

	}
	$scope.load_all();
	$scope.load_all_dc();
	$scope.reset();
	$scope.load_tinh();
})