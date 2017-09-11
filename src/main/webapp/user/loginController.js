(function() {

    angular.module("synxApp")
        .controller("loginController", ["$scope", "$state", "$rootScope",
            "userSvc",
            function($scope, $state, $rootScope, userSvc) {


                $scope.login = function() {
                    userSvc.authenticateUser($scope.user)
                    .then(function(response) {
                        var user = JSON.stringify(response.data);
                        localStorage.setItem("currentUser", user);
                        $rootScope.user = JSON.parse(localStorage.getItem("currentUser"));
                        //console.log($rootScope.user);

                        var token = response.headers("x-auth-token");
                        localStorage.setItem("token", token);

                        $state.go("home");
                    },
                    function(error) {
                        $scope.alertMessage = "Incorrect username or password";
                    });
                };
            }
        ]);

})();