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
                })

                .state("users", {
                    url: "/users",
                    templateUrl: "user/users.html",
                    controller: "usersController"
                })

                .state("user", {
                    url: "/user/{username}",
                    templateUrl: "user/profile.html",
                    controller: "profileController",
                    resolve: {
                        user: function($stateParams, userService) {
                            return userService.getUserById($stateParams.username)
                                .then(function(response) {
                                    return response.data;
                                });
                        }
                    }
                })
                
                .state("user.saved", {
                    url: "/saved",
                    views: {
                        "saved": {
                            templateUrl: "user/profile-views/saved.html"
                        }
                    }
                })
                
                .state("user.upvoted", {
                    url: "/upvoted",
                    views: {
                        "upvoted": {
                            templateUrl: "user/profile-views/upvoted.html"
                        }
                    }
                });
        }
        ]);
})();
