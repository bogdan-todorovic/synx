(function() {
    angular.module("synxApp")
        .controller("userController", ["$scope", "$state", "userService", function($scope, $state, userService) {

            
            userService.getAllUsers()
                .then(function(response) {
                    $scope.users = response.data;
                    console.log($scope.users);
                }, 
                function(error) {
                    console.log("Can't load users");
                });

            $scope.updateRoles = function() {
                $scope.users.forEach(user => {
                    if (user.newRole === "ADMIN" || user.newRole === "USER" || user.newRole === "MODERATOR") {
                        userService.updateRoles(user.username, user.newRole)
                            .then(function(response) {
                                $scope.users.splice(user.username, 1, response.data);
                            }, function(error) {
                                console.log("Unable to update role of " + user.username);
                            });
                    }
                });
            }
        }]);
})();