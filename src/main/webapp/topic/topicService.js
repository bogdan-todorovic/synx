(function() {
    angular.module("synxApp")
        .service("topicService", ["$http", function($http) {

            var self = this;
            self.baseUrl = "rest/topics";

            self.getTopicsBySubforum = function(id) {
                return $http.get(self.baseUrl + "/subforum/" + id);
            };
        }]);
})();