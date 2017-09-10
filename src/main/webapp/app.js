(function() {
    var app = angular.module("synxApp", ["ui.router"]);

    app.config(["$urlRouterProvider", "$stateProvider", function($urlRouterProvider, $stateProvider) {

        $urlRouterProvider.otherwise("/home");

        $stateProvider

            .state("home", {
                url: "/home",
                templateUrl: "home/home.html"
            });
    }]);
})();