<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="agjs.bean.restaurant.*"%>


<%
RestaurantTimeMoneyVo restaurantTimeMoneyVo = (RestaurantTimeMoneyVo) request.getAttribute("restaurantTimeMoneyVo");
%>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<link rel="icon" href="img/logo.ico" type="image/x-icon" />
<title>��x�޲z | �\�U�޲z | ����</title>
<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet" />
<link href="css/ResuaurantBack.css" rel="stylesheet" />
<link href="css/sb-admin-2.min.css" rel="stylesheet" />
</head>

<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">
			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="index.html">
				<div class="sidebar-brand-icon rotate-n-15">
					<i class="fas fa-laugh-wink"></i>
				</div>
				<div class="sidebar-brand-text mx-3">��x�޲z</div>
			</a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0" />

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="index.html"> <i class="fas fa-fw fa-tachometer-alt"></i> <span>���i�޲z</span></a>
			</li>

			<!-- Divider -->
			<hr class="sidebar-divider my-0" />

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="index.html"> <i class="fas fa-fw fa-tachometer-alt"></i> <span>�q��ק�޲z</span></a>
			</li>

			<!-- Divider -->
			<hr class="sidebar-divider my-0" />

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="roomManagement.html"> <i
					class="fas fa-fw fa-tachometer-alt"></i> <span>�ж��޲z</span></a></li>

			<!-- Divider -->
			<hr class="sidebar-divider my-0" />
			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="index.html"> <i class="fas fa-fw fa-tachometer-alt"></i> <span>��{�޲z</span></a>
			</li>
			<!-- Divider -->
			<hr class="sidebar-divider my-0" />
			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="report.html"> <i class="fas fa-fw fa-tachometer-alt"></i>
					<span>�]�ȳ���</span></a></li>

			<!-- Divider -->
			<hr class="sidebar-divider my-0" />
			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="restaurantBackStage.html"> <i
					class="fas fa-fw fa-tachometer-alt"></i> <span>�\�U�޲z</span></a></li>

			<!-- Divider -->
			<hr class="sidebar-divider my-0" />
			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="index.html"> <i class="fas fa-fw fa-tachometer-alt"></i> <span>�ȪA</span></a>
			</li>
			<!-- Divider -->
			<hr class="sidebar-divider" />
			<!-- Heading -->
		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<div class="topbar-divider d-none d-sm-block"></div>

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small">�޲z��</span> <img
								class="img-profile rounded-circle"
								src="image/undraw_profile.svg" />
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#"> <i
									class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Profile
								</a> <a class="dropdown-item" href="#"> <i
									class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
									Settings
								</a> <a class="dropdown-item" href="#"> <i
									class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
									Activity Log
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Logout
								</a>
							</div></li>
					</ul>
				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">
					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4"></div>

					<!-- �\�U��T���� -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h4 class="font-weight-bold text-primary">�\�U��T����</h4>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/admin/restaurantTimeMoney"
								name="form1">
								<div class="modal-dialog modal-lg" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">��s�u�f</h5>
										</div>
										<div class="modal-body">
											<div class="container-fluid ">
												<div class="row card-header ">
													<div class="col-md-12">
														<div class="mb-3">
															<input type="text" class="form-control"name="REST_ID"
																id="exampleFormControlInput1" placeholder="�\�U�W��">
														</div>
														<div class="mb-3">
															<textarea class="form-control"name="MONEY_CONTEXT"
																id="exampleFormControlTextarea1" rows="3"
																placeholder="�u�f�W��"></textarea>
														</div>
														<div class="card-header">����</div>
														<div class="col-md-12 card-body ">
															<div class="form-check form-check-inline">
																<input type="text" class="form-control"name="MONEY"
																	id="exampleFormControlInput1" placeholder="$1200">
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary">
												<a href="restaurantBackStage.html">����</a>
											</button>
											<input type="hidden" name="action" value="insert"> <input
												type="submit" value="��s" class="btn btn-primary">
										</div>
									</div>
								</div>
							</FORM>

						</div>
					</div>


				</div>




				<!-- ���� copyright -->
				<div class="card-body" id="roomList">
					<footer class="sticky-footer bg-white">
						<div class="container my-auto">
							<div class="copyright text-center my-auto">
								<span class="copyright"> &copy; 2022, A GooD Journey
									SySTem, Inc.�Ψ���ݤ��q</span>
							</div>
						</div>
					</footer>
				</div>
				<!-- End of Content Wrapper -->
			</div>
			<!-- End of Page Wrapper -->

			<!-- �ܳ�Button-->
			<a class="scroll-to-top rounded" href="#page-top"> <i
				class="fas fa-angle-up"></i>
			</a>

			<!-- �n�X���s-->
			<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Ready to
								Leave?</h5>
							<button class="close" type="button" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">��</span>
							</button>
						</div>
						<div class="modal-body">Select "Logout" below if you are
							ready to end your current session.</div>
						<div class="modal-footer">
							<button class="btn btn-secondary" type="button"
								data-dismiss="modal">Cancel</button>
							<a class="btn btn-primary" href="login.html">Logout</a>
						</div>
					</div>
				</div>
			</div>

			<!-- Bootstrap core JavaScript-->
			<script src="vendor/jquery/jquery.min.js"></script>
			<!-- �u�� -->
			<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

			<!-- Core plugin JavaScript-->
			<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

			<!-- Custom scripts for all pages-->
			<script src="js/sb-admin-2.min.js"></script>

			<!-- Page level plugins -->
			<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

			<!-- Page level custom scripts -->
			<script src="js/demo/datatables-demo.js"></script>
</body>

</html>