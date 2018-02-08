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

            self.getUserById = function(id) {
                return $http.get(self.urlBase + "/" + id);
            };

            self.getAllModerators = function() {
                return $http.get(self.urlBase + "/moderators");
            };

            self.getSavedTopics = function(username) {
                return $http.get(self.urlBase + "/savedtopics/" + username);
            };

            self.getSavedComments = function(username) {
                return $http.get(self.urlBase + "/savedcomments/" + username);
            };

            self.getUpvotedTopics = function(username) {
                return $http.get(self.urlBase + "/upvotedtopics/" + username);
            };

            self.getDownvotedTopics = function(username) {
                return $http.get(self.urlBase + "/downvotedtopics/" + username);
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
            
            self.updateUser = function(id, data) {
                return $http.put(self.urlBase + "/" + id, data);
            };
        }]);
})();