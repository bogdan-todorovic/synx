(function() {
    angular.module("synxApp")
        .controller("userController", ["$scope", "userService", function($scope, userService) {

            function getAll() {
                userService.getAllUsers()
                    .then(function(response) {
                        $scope.users = response.data;
                        console.log($scope.users);
                    }, 
                    function(error) {
                        console.log("Can't load users");
                    });
            }
            getAll();

            $scope.updateRoles = function() {
                $scope.users.forEach(user => {
                    if (user.newRole === "ADMIN" || user.newRole === "USER" || user.newRole === "MODERATOR") {
                        console.log(user.newRole);
                        userService.updateRoles(user.username, user.newRole)
                            .then(function(response) {
                                console.log("Role successfully changed" + response.data);
                            }, function(error) {
                                console.log("Unable to update role" + error.data);
                            });
                    }
                });
                getAll();
            }
        }]);
})();