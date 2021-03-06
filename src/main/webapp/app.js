(function() {
    var app = angular.module("synxApp", ["ui.router"]);

    app.config(["$urlRouterProvider", "$stateProvider", "$httpProvider",
        function($urlRouterProvider, $stateProvider, $httpProvider) {
        
        $urlRouterProvider.otherwise("/home");

        $stateProvider

            .state("home", {
                url: "/home",
                templateUrl: "home/home.html",
                controller: "homeController",
                resolve: {
                    topics: function(topicService) {
                        return topicService.getAllTopics()
                            .then(function(response) {
                                return response.data;
                            });
                    }    
                }
            });

        $httpProvider.interceptors.push(["$q", "$injector", "$rootScope", function($q, $injector, $rootScope) {
            return {
                request: function(config) {
                    var token = localStorage.getItem("token");
                    if (token) {
                        config.headers["x-auth-token"] = token;
                        $rootScope.user = JSON.parse(localStorage.getItem("currentUser"));
                    }
                    return config;
                },
                responseError: function(error) {
                    if (error.status === 401 || error.status === 403) {
                        var stateService = $injector.get("$state");
                        localStorage.removeItem("token");
                        localStorage.removeItem("currentUser");
                        //console.log(stateService);
                        stateService.go("login");
                    }
                    return $q.reject(error);
                }
            };
        }]);

    }]);
})();