let host = "http://localhost:8080/rest";
const app = angular.module("AdminSpApp", []);
app.controller("AdminSpCtrl", function($scope, $http) {
	$scope.form = {};
	$scope.items = [];
	$scope.nhasanxuats = [];
	$scope.errorMessage = '';



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
		//Lỗi bỏ trống tên sản phẩm 
		if (!$scope.form.tenSanPham) {
			alert("Vui lòng nhập tên sản phẩm!!")
			/*$scope.errorMessage = "Vui lòng nhập tên sản phẩm!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}
		//Lỗi trùng tên sản phẩm
		let existingProduct = $scope.items.find(item => item.tenSanPham === $scope.form.tenSanPham);
		if (existingProduct) {
			alert("tên sản phẩm đã tồn tại!!")
			/*$scope.errorMessage = "Tên sản phẩm đã tồn tại!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}

		//Lỗi bỏ trống đơn giá
		if (!$scope.form.donGia) {
			alert("Vui lòng nhập đơn giá!!")
			/*$scope.errorMessage = "Vui lòng nhập đơn giá!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}
		//Lỗi đơn giá nhỏ hơn 0
		if ($scope.form.donGia < 0) {
			alert("đơn giá không được nhỏ hơn 0!!")

			return;
		}
		//Lỗi đơn giá nhỏ hơn 0
		if ($scope.form.donGia > 100000000) {
			alert("đơn giá không thể quá 100 triệu!!")

			return;
		}
		//Lỗi đơn giá nhỏ hơn 0
		if ($scope.form.soLuongTon < 0) {
			alert("số lượng không được nhỏ hơn 0!!")

			return;
		}

		//Lỗi bỏ trống số lượng
		if (!$scope.form.soLuongTon) {
			alert("Vui lòng nhập số lượng!!")
			/*$scope.errorMessage = "Vui lòng nhập số lượng!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}
		//Lỗi bỏ trống ram
		if (!$scope.form.ram) {
			alert("Vui lòng nhập ram!!")
			/*$scope.errorMessage = "Vui lòng nhập ram!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}
		//Lỗi ram nhỏ hơn 0
		if ($scope.form.ram < 0) {
			alert("số lượng không được nhỏ hơn 0!!")

			return;
		}
		//Lỗi bỏ trống rom 
		if (!$scope.form.rom) {
			alert("Vui lòng nhập rom!!")
			/*$scope.errorMessage = "Vui lòng nhập rom!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}
		//Lỗi rom nhỏ hơn 0
		if ($scope.form.rom < 0) {
			alert("số lượng không được nhỏ hơn 0!!")

			return;
		}
		//Lỗi bỏ trống pin
		if (!$scope.form.pin) {
			alert("Vui lòng nhập pin!!")
			/*$scope.errorMessage = "Vui lòng nhập pin!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}

		//Lỗi pin nhỏ hơn 0
		if ($scope.form.pin < 0) {
			alert("số lượng không được nhỏ hơn 0!!")

			return;
		}

		//Lỗi bỏ trống chip
		if (!$scope.form.chip) {
			alert("Vui lòng nhập chip!!")
			/*$scope.errorMessage = "Vui lòng nhập chip!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}
		//Không chọn thương hiệu
		if (!$scope.form.nhaSanXuat || !$scope.form.nhaSanXuat.id) {
			alert("vui lòng chọn nhà sản xuất")
			/*	$scope.errorMessage = "Vui lòng chọn thương hiệu!";
				$('#errorModal').modal('show'); // Show the modal*/
			return;
		}




		var item = angular.copy($scope.form);
		var url = `${host}/sanpham`;
		$http.post(url, item).then(resp => {

			$scope.items.push(item);

			$scope.reset();
			$scope.errorMessage = ''; // Xóa thông báo lỗi khi thành công
			alert("Thêm mới thành công");
			console.log("Success", resp);

			$('#errorModal1').modal('show'); // Show the modal
			location.reload();
		}).catch((error) => {
			alert("thêm thất bại!")
			console.log("Error", error);
		});
	}

	$scope.update = function() {

		//Lỗi bỏ trống tên sản phẩm 
		if (!$scope.form.tenSanPham) {
			alert("Vui lòng nhập tên sản phẩm!!")
			/*$scope.errorMessage = "Vui lòng nhập tên sản phẩm!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}

		/*//Lỗi trùng tên sản phẩm
		let existingProduct = $scope.items.find(item => item.tenSanPham === $scope.form.tenSanPham);
		if (existingProduct) {
			alert("tên sản phẩm đã tồn tại!!")
			$scope.errorMessage = "Tên sản phẩm đã tồn tại!!";
			$('#errorModal').modal('show'); // Show the modal
			return;
		}*/

		//Lỗi bỏ trống đơn giá
		if (!$scope.form.donGia) {
			alert("Vui lòng nhập đơn giá!!")
			/*$scope.errorMessage = "Vui lòng nhập đơn giá!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}
		//Lỗi đơn giá nhỏ hơn 0
		if ($scope.form.donGia < 0) {
			alert("đơn giá không được nhỏ hơn 0!!")

			return;
		}
		//Lỗi đơn giá nhỏ hơn 0
		if ($scope.form.donGia > 100000000) {
			alert("đơn giá không thể quá 100 triệu!!")

			return;
		}
		//Lỗi đơn giá nhỏ hơn 0
		if ($scope.form.soLuongTon < 0) {
			alert("số lượng không được nhỏ hơn 0!!")

			return;
		}

		//Lỗi bỏ trống số lượng
		if (!$scope.form.soLuongTon) {
			alert("Vui lòng nhập số lượng!!")
			/*$scope.errorMessage = "Vui lòng nhập số lượng!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}
		//Lỗi bỏ trống ram
		if (!$scope.form.ram) {
			alert("Vui lòng nhập ram!!")
			/*$scope.errorMessage = "Vui lòng nhập ram!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}
		//Lỗi ram nhỏ hơn 0
		if ($scope.form.ram < 0) {
			alert("số lượng không được nhỏ hơn 0!!")

			return;
		}
		//Lỗi bỏ trống rom 
		if (!$scope.form.rom) {
			alert("Vui lòng nhập rom!!")
			/*$scope.errorMessage = "Vui lòng nhập rom!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}
		//Lỗi rom nhỏ hơn 0
		if ($scope.form.rom < 0) {
			alert("số lượng không được nhỏ hơn 0!!")

			return;
		}
		//Lỗi bỏ trống pin
		if (!$scope.form.pin) {
			alert("Vui lòng nhập pin!!")
			/*$scope.errorMessage = "Vui lòng nhập pin!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}

		//Lỗi pin nhỏ hơn 0
		if ($scope.form.pin < 0) {
			alert("số lượng không được nhỏ hơn 0!!")

			return;
		}

		//Lỗi bỏ trống chip
		if (!$scope.form.chip) {
			alert("Vui lòng nhập chip!!")
			/*$scope.errorMessage = "Vui lòng nhập chip!!";
			$('#errorModal').modal('show'); // Show the modal*/
			return;
		}
		//Không chọn thương hiệu
		if (!$scope.form.nhaSanXuat || !$scope.form.nhaSanXuat.id) {
			alert("vui lòng chọn nhà sản xuất")
			/*	$scope.errorMessage = "Vui lòng chọn thương hiệu!";
				$('#errorModal').modal('show'); // Show the modal*/
			return;
		}



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


	}

	// Tìm kiếm sản phẩm 
	$scope.searchProductByName = function() {
		if ($scope.searchKeyword && $scope.searchKeyword.trim() !== "") {
			$http.get("/rest/sanpham/search", {
				params: { keyword: $scope.searchKeyword }
			}).then(resp => {
				$scope.items = resp.data;
			}).catch(error => {
				alert("lỗi tìm kiếm sản phẩm")
				console.log("Error", error);
			});
		} else {
			// Nếu không có từ khóa tìm kiếm, hiển thị tất cả sản phảm
			$scope.load_all_nsx();
			$scope.load_all();
		}
	};


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
