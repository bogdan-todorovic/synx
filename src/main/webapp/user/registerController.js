(function() {

    angular.module("synxApp")
        .controller("registerController", ["$scope", "$state", "userSvc", function($scope, $state, userSvc) {
            
            $scope.create = function() {
                userSvc.register($scope.user)
                .then(function(response) {
                    $state.go("login");
                },
                function(error) {
                    $scope.alertMessage = "A user with that username already exists";
                });
            };
        }]);
})();