(function() {
    
    angular.module("synxApp")
        .controller("loginController", ["$scope", "$state", "userSvc", function($scope, $state, userSvc) {


            $scope.login = function() {
                userSvc.authenticateUser($scope.user)
                .then(function(response) {
                    var user = JSON.stringify(response.data);
                    localStorage.setItem("currentUser", user);
                    var cu = localStorage.getItem("currentUser");
                    //console.log(JSON.parse(cu));

                    var token = response.headers("x-auth-token");
                    localStorage.setItem("token", token);

                    $state.go("home");
                },
                function(error) {
                    $scope.alertMessage = "Incorrect username or password";
                });
            };


        }]);

})();