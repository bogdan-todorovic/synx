(function() {
    angular.module("synxApp")
        .controller("userController", ["$scope", "userService", function($scope, userService) {

            userService.getAllUsers()
                .then(function(response) {
                    $scope.users = response.data;
                    console.log($scope.users);
                }, 
                function(error) {
                    console.log("Can't load users");
                });

        }]);
})();