var app = angular.module('app', []);
var config = {headers: {'Content-Type': 'application/json;charset=utf-8;'}};

app.controller('appController', function ($scope, $http) {

    $scope.getTypes = function () {
        // var url = $location.absUrl() + "getTypes";
        var url = "getTypes";
        $http.get(url, config).then(function (response) {
            $scope.types = response.data;
            $scope.getResultMessage = "Not fail!";
        }, function (response) {
            $scope.getResultMessage = "Fail!";
        });
    };

    $scope.getProducts = function () {
        var url = "getProducts";
        $http.get(url, config).then(function (response) {
            $scope.products = response.data;
        });
    };

    $scope.getQuantity = function(productStockLinks) {
        var sum = 0;
        for (i=0; i<productStockLinks.length; i++){
            sum+=productStockLinks[i].quantity;
        }
        return sum;
    };

    $scope.isEmptyBasket = function (basketProductLinks) {
        for (i=0; i<basketProductLinks.length; i++){
            if (basketProductLinks[i].quantity>0) return false;
        }
        return true;
    };

    $scope.getUsername = function () {
        var url = "getUsername";
        $http.get(url, config).then(function (response) {
            $scope.username = response.data;
        });
    };

    $scope.getLastUserBasket = function () {
        var url = "getLastUserBasket";
        $http.get(url, config).then(function (response) {
            $scope.basket = response.data;
        });
    };

    $scope.getUserBaskets = function () {
        var url = "getUserBaskets";
        $http.get(url, config).then(function (response) {
            $scope.baskets = response.data;
        });
    };
});

app.controller('postController', function ($scope, $http, $location, $window) {

    $scope.submitRegistration = function () {
        var url = "submitRegistration";
        var data = {
            name: $scope.name,
            password: $scope.password
        };
        $http.post(url, data, config).then(function (response) {
            $scope.answer = response.data;
            if ($scope.answer === "true") {
                $window.location.href = '/login';
            } else {
                $scope.postResultMessage = "Ошибка регистрации";
            }
        });
    };

    $scope.searchProduct = function () {
        $scope.res = "1";
        var url = "searchProduct";
        var data = {
            name: $scope.name,
            price: $scope.price
        };
        var q=0;
        if ($scope.quantity!=null && $scope.quantity!=="") q=$scope.quantity;
        var config = {
            headers: {'Content-Type': 'application/json;charset=utf-8;'},
            params: {'typename': $scope.typename,
                    'quantity': q }
        };
        $http.post(url, data, config).then(function (response) {
            $scope.res = response.data;
        });
    };

    var id;
    $scope.setId = function (productId) {
        id = productId;
    };
    $scope.addProduct = function () {
        var url = "addProduct";
        var data = {
            id: id
        };
        $scope.openPopUp("Товар добавлен в корзину");
        $http.post(url, data, config).then(function (response) {
            // $window.location.href = '/shop/product';
        });
    };

    $scope.showPopUpMsg = false;
    $scope.openPopUp = function (text) {
        $scope.showPopUpMsg = true;
        $scope.popUpMsgContent = text;
    };

    $scope.deleteProduct = function () {
        var url = "deleteProduct";
        var data = {
            id: id
        };
        $http.post(url, data, config).then(function (response) {
            $window.location.href = '/shop/basket';
        });
    };

    $scope.confirmBasket = function () {
        var url = "confirmBasket";
        $http.post(url, config).then(function (response) {
            $window.location.href = '/shop/basket';
        });
    };
});

app.directive('popUpMsg', function () {
    return {
        restrict: 'E',
        scope: false,
        template: '<div id="popUpMsg-bg" ng-show="showPopUpMsg">' +
        '<div id="popUpMsg"><div class="close" ng-click="closePopUp()">X</div>' +
        '<div class="content">{{popUpMsgContent}}</div>' +
        '<div ng-if="popUpMsgContent.length==24"><button ng-click="goToBasket()">Перейти в корзину</button></div>' +
        '<div ng-if="popUpMsgContent.length==25"><button ng-click="loginUser()">Войти</button></div>' +
        '</div></div>',
        controller: function ($scope, $window, $location) {
            $scope.closePopUp = function () {
                if ($location.absUrl()==="http://localhost:8080/shop/product") {
                    $scope.showPopUpMsg = false;
                    $window.location.href = '/shop/product';
                }else{
                    $scope.showPopUpMsg = false;
                    $window.location.href = '/shop/search';
                }
            };
            $scope.loginUser = function () {
                $window.location.href = '/login';
            }
            $scope.goToBasket = function () {
                $window.location.href = '/shop/basket';
            }
        }
    }
});