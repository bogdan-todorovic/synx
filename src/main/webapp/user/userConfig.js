(function() {
    angular.module("synxApp")
        .config(["$stateProvider", function($stateProvider) {

            $stateProvider
                .state("login", {
                    url: "/login",
                    templateUrl: "user/login.html",
                    controller: "loginController"
                })
                
                .state("register", {
                    url: "/register",
                    templateUrl: "user/register.html",
                    controller: "registerController"
                });
        }
        ]);
})();
