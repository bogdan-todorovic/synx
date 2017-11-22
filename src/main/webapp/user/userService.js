(function() {
    angular.module("synxApp")
        .service("userService", ["$http", function($http) {

            var self = this;
            self.urlBase = "rest/users";

            self.authenticateUser = function(data) {
                return $http.post(self.urlBase + "/login", data);
            };

            self.register = function(data) {
                return $http.post(self.urlBase + "/register", data);
            };

            self.getAllUsers = function() {
                return $http.get(self.urlBase);
            };


            self.updateRoles = function(id, newRole) {
                var req = {
                    method: 'PUT',
                    url: self.urlBase + "/newrole/" + id,
                    headers: {
                        'Content-Type': 'text/plain'
                    },
                    data: newRole
                }
               
                return $http(req);
            };
            
        }]);
})();