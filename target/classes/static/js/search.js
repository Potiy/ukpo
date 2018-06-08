var app = angular.module('search', []);

app.controller("SearchController", function ($scope, $http) {
    $scope.search = function (id) {
        if (id === "") id = undefined;
        $http.post('/rest/search/' + id).success(function (data, status, headers, config) {
                $scope.isSearchError = false;
                $scope.houseList = data;
            }
        ).error(function (data, status, headers, config) {
            $scope.errorMessage = data.message;
            $scope.isSearchError = true;
            console.error(status, data, headers);
        });
    };
});

