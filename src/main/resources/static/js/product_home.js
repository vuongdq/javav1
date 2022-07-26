var app = angular.module('myApp',['ngMaterial']);
app
    .controller('MyController', function($scope, $http) {

        $scope.showw = false;
        $scope.chuyenvechedosua = function(){
            $scope.showw =!$scope.showw;
        }
        $scope.shownow =function(product){
            product.showw = !product.showw;
        }


        $http.get("http://vuongdq:8080/api/v1")
            .then(function(res) {
                $scope.products = res.data;
                //console.log(res.data);
            });

        $scope.saveData = function(product){

            //thay doi front end
            product.showw=!product.showw;

            var data = $.param({
                id:product.id,
                productName:product.productName,
                price:product.price,
                price:product.price,
                url:product.url
            });

            console.log(data);
        }


    })
