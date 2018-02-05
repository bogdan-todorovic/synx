(function() {
    angular.module("synxApp")
        .controller("usersController", ["$scope", "userService", "$filter", function($scope, userService, $filter) {

            
            userService.getAllUsers()
                .then(function(response) {
                    $scope.users = response.data;
                    $scope.filteredUsers = response.data;
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

            $scope.search = function(searchText) {
                $scope.filteredUsers = $filter("filter")($scope.users, searchText);
            }
        }]);
})();