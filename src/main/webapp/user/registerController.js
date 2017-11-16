(function() {

    angular.module("synxApp")
        .controller("registerController", ["$scope", "$state", "userService", function($scope, $state, userService) {
            
            $scope.create = function() {
                userService.register($scope.user)
                .then(function(response) {
                    $state.go("login");
                },
                function(error) {
                    $scope.alertMessage = "A user with that username already exists";
                });
            };
        }]);
})();