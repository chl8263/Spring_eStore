var cartApp = angular.module('cartApp', []);
// 모델 // 서비스
cartApp.controller("cartCtrl", function($scope, $http) { // 생성자 밑에 function은
															// 메소드

	$scope.initCartId = function(cartId) {
		$scope.cartId = cartId;
		$scope.refreshCart();
	};
	// 짧은 포맷
	$scope.refreshCart = function() {
		$http.get('/eStore/api/cart/' + $scope.cartId).then(
				function successCallback(response) {
					$scope.cart = response.data;
				});
	};
	// 긴 포맷
	$scope.clearCart = function() {
		$scope.setCsrfToken();

		$http({
			method : 'DELETE',
			url : '/eStore/api/cart/' + $scope.cartId
		}).then(function successCallback() {
			$scope.refreshCart();
		}, function errorCallback(response) {
			console.log(response.data);
		});
	};

	$scope.addToCart = function(productId) {
		$scope.setCsrfToken();

		$http.put('/eStore/api/cart/add/' + productId).then(
				function successCallback() {
					alert("Product successfully added to the cart!");
				}, function errorCallback() {
					alert("Adding to the cart failed!");
				});
	};
	
	$scope.plusItem = function(productId) {
		$scope.setCsrfToken();

		$http.put('/eStore/api/cart/plus/' + productId).then(
				function successCallback() {
					$scope.refreshCart();
				}, function errorCallback() {
					$scope.refreshCart();
				});
	};
	
	$scope.minusItem = function(productId) {
		$scope.setCsrfToken();

		$http.put('/eStore/api/cart/minus/' + productId).then(
				function successCallback() {
					$scope.refreshCart();
				}, function errorCallback() {
					$scope.refreshCart();
				});
	};

	$scope.removeFromCart = function(productId) {
		$scope.setCsrfToken();

		$http({
			method : 'DELETE',
			url : '/eStore/api/cart/cartitem/' + productId
		}).then(function successCallback() {
			$scope.refreshCart();
		}, function errorCallback(response) {
			console.log(response.data);
		});
	};

	$scope.plusFromCart = function(productId) {
		$scope.setCsrfToken();

		$http({
			method : 'DELETE',
			url : '/eStore/api/cart/cartitem/' + productId
		}).then(function successCallback() {
			$scope.refreshCart();
		}, function errorCallback(response) {
			console.log(response.data);
		});
	};

	$scope.minusFromCart = function(productId) {
		$scope.setCsrfToken();

		$http({
			method : 'DELETE',
			url : '/eStore/api/cart/cartitem/' + productId
		}).then(function successCallback() {
			$scope.refreshCart();
		}, function errorCallback(response) {
			console.log(response.data);
		});
	};

	$scope.calGrandTotal = function() {
		var grandTotal = 0;

		for (var i = 0; i < $scope.cart.cartItems.length; i++) {
			grandTotal += $scope.cart.cartItems[i].totalprice;
		}

		return grandTotal;
	};

	$scope.setCsrfToken = function() {
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");

		$http.defaults.headers.common[csrfHeader] = csrfToken;
	};
});