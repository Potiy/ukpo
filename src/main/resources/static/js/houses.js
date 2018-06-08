var app = angular.module('houses', []);

app.controller("HousesController", function ($scope, $http) {

    $scope.getHouses = function () {
        $http.get('/rest/houses').success(function (data, status, headers, config) {
            $scope.houseList = data;

        }).error(function (data, status, headers, config) {
            if (data.message === 'Time is out') {
                $scope.finishByTimeout();
            }
        });
    };

    $scope.delete = function (id) {

        if (id === "") id = undefined;

        $http.delete('/rest/houses/delete/' + id).success(function (data, status, headers, config) {
            for (var i = 0, len = $scope.houseList.length; i < len; i++) {
                var j = $scope.houseList[i];
                if (j.id === id) {
                    $scope.houseList.splice(i, 1);
                    break;
                }
            }
        }).error(function (data, status, headers, config) {
            $scope.errorMessage = data.message;
            $scope.isAddError = false;
            console.error(status, data, headers);
        });
    };

    $scope.addHouses = function (id, name, address, phone, cost) {

        if (id === "") id = undefined;
        if (name === "") name = undefined;
        if (address === "") address = undefined;
        if (phone === "") phone = undefined;
        if (cost === "") cost = undefined;

        $http.post('/rest/houses/add/' + id + "/" + name + "/" + address + "/" + phone + "/" + cost).success(function (data, status, headers, config) {
                $scope.houseList.splice(0, 0, data);
            }
        ).error(function (data, status, headers, config) {
            $scope.errorMessage = data.message;
            $scope.isAddError = true;
            console.error(status, data, headers);
        });

    };
});
