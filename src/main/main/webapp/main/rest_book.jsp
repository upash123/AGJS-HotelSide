<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="agjs.bean.restaurant.*"%>


<%
RestaurantBookVO restaurantBookVO = (RestaurantBookVO) request.getAttribute("restaurantBookVO");
%>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>| �����W�b | �q�� |</title>
<link rel="stylesheet" href="style/AGJS_2.css">
<!-- <link rel="stylesheet" href="style/AGJS.css"> -->
<link rel="icon" href="image/logo.ico" type="image/x-icon" />
</head>

<body>

	<main>
		<div class="pic_book">
			<div class="picture">
				<img src="image/hotelRSRT-1.png" alt="">
			</div>
			<div class="picture">
				<img src="image/hotelRSRT-2.png" alt="">
			</div>
			<div class="picture">
				<img src="image/hotelRSRT-3.png" alt="">
			</div>
		</div>
		<div class="reservation">
			<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
			<div>
				<p>*������</p>
			</div>

			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/main/rest_book" name="form1">
				<div class="elem-group">
					<label for="restaurant-selection">����\�U*</label> <input type="text"
						id="restaurant" placeholder="Java Steak House" required
						class="restaurant" name="REST_ID">
				</div>
				<div class="elem-group">
					<label for="name">�m�W*</label> <input type="text" id="name"
						name="USER_NAME" name="visitor_name" placeholder="���p��"
						pattern=[\u4e00-\u9fa5]{2,4} required class="name">
				</div>
				<div class="elem-group">
					<label for="phone">������X*</label> <input type="tel" id="phone"
						name="REST_TEL" placeholder="0912-345-678"
						pattern=(\d{4})-?\s?(\d{3})-?\s?(\d{3}) required class="phone">
				</div>
				<hr>
				<div class="elem-group inlined">
					<label for="people">�H��*</label> <input type="number" id="people"
						name="REST_NUM" placeholder="1" min="1" required class="people">
				</div>
				<div class="elem-group inlined">
					<label for="reservation-date">�q����*</label> <input type="date"
						id="f_date1" name="REST_DATE" required class="date">
				</div>

				<hr>
				<div class="elem-group">
					<label for="message">�S��Ƶ�</label>
					<textarea id="message" name="visitor_message" name="REST_NOTE"
						placeholder="Tell us anything else that might be important."
						class="note"></textarea>
				</div>
				<div class="modal-footer">
					<input type="hidden" name="action" value="insert"> <input
						type="submit" value="�T�{�q��" class="btn btn-primary">
				</div>
			</Form>
		</div>
	</main>
	<!--///////////////////////////////////////////////////////////////////////////////////////////////////////////-->
	<script src="vendors/jquery/jquery-3.6.0.min.js"></script>
	<script src="js/booking.js"></script>
	<script
		src="https://code.iconify.design/iconify-icon/1.0.0-beta.3/iconify-icon.min.js"></script>
	<script src="js/header_footer.js"></script>
</body>




</html>