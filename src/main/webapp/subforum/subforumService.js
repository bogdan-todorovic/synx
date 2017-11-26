(function() {
    angular.module("synxApp")
        .service("subforumService", ["$http", function($http) {

            var self = this;
            self.baseUrl = "rest/subforums";

            self.getAllSubforums = function() {
                return $http.get(self.baseUrl);
            };

            self.getSubforum = function(id) {
                return $http.get(self.baseUrl + "/" + id);
            };

            self.createSubforum = function(data) {
                return $http.post(self.baseUrl, data);
            };

            self.removeSubforum = function(id) {
                return $http.delete(self.baseUrl + "/" + id);
            };

            self.updateSubforum = function(data) {
                return $http.put(self.baseUrl + "/" + data.title, data);
            };

        }]); 
})();