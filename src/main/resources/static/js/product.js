var app = angular.module('myApp',['ngMaterial']);
app
    .controller('MyController', function($scope, $http) {

        $scope.hienthi = false;
        $scope.chuyenvechedosua = function(){
            $scope.hienra =!$scope.hienra;
        }
        $scope.hienradi =function(motnguoi){
            motnguoi.hienra = !motnguoi.hienra;
        }
        // $scope.nhieunguoi = [
        //    {
        //       ten: "vuong",
        //       facebook: "fb.com/v",
        //       namsinh: "1988",
        //       sodienthoai:"098888215"
        //    },
        //    {
        //       ten: "linh",
        //       facebook: "fb.com/linh",
        //       namsinh: "1995",
        //       sodienthoai:"03333555"
        //    },
        //    {
        //       ten: "nhan",
        //       facebook: "fb.com/thiennhan",
        //       namsinh: "2017",
        //       sodienthoai:"0999555444"
        //    }
        // ]

        $http.get("http://vuongdq:8080/api/v1")
            .then(function(res) {
                $scope.nhieunguoi = res.data;
                //console.log(res.data);
            });

        $scope.luudulieu = function(nguoi){

            //thay doi front end
            nguoi.hienra=!nguoi.hienra;

            var data = $.param({
                id:product.id,
                ten:product.productName,
                facebook:product.price,
                sodienthoai:product.price,
                namsinh:product.url
            });
            var config={
                headers:{
                    'content-type':'application/x-www-form-urlencoded; charset=UTF-8'
                }
            }



            $http.post('http://vuongdq:8080/api/v1/insert', data, config).then(function(res){
                console.log(res.data);
            }, function(res) { console.log(res.data); });
        }


    })
