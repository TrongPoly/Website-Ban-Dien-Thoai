function order() {
	const select = document.getElementById("diaChiGiaoHang");
	if(select===null){
		alert("Vui lòng thêm địa chỉ nhận hàng");
	}
	const selectedOption = select.options[select.selectedIndex];
	const diachi = selectedOption.value;
	
	$.ajax(
		{
			url: "/rest/order",
			type: "POST",
			data: {
				"diaChiId": diachi,
			},
			success: function(data) {
				alert("Đặt hàng thành công")
				location.href = "http://localhost:8080/order/index"
			},
			error: function(error) {
				if(error.status===404){
					alert("Số lượng sản phẩm còn lại không đủ để đặt hàng")
					location.reload();
				}
			}
		}
	);
}