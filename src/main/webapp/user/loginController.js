(function() {
    
    angular.module("synxApp")
        .controller("loginController", ["$scope", "userSvc", function($scope, userSvc) {


            $scope.login = function() {
                userSvc.authenticateUser($scope.user)
                .then(function(response) {
                    console.log(response);
                });
            };


        }]);

})();