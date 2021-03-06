(function() {
    angular.module("synxApp")
        .service("topicService", ["$http", function($http) {

            var self = this;
            self.baseUrl = "rest/topics";

            self.getAllTopics = function() {
                return $http.get(self.baseUrl);
            };

            self.getTopicsBySubforum = function(id) {
                return $http.get(self.baseUrl + "/subforum/" + id);
            };

            self.createTopic = function(topic) {
                return $http.post(self.baseUrl, topic);
            };

            self.getTopic = function(id) {
                return $http.get(self.baseUrl + "/" + id)
            };

            self.deleteTopic = function(id) {
                return $http.delete(self.baseUrl + "/" + id);
            };

            self.updateTopic = function(id, topic) {
                return $http.put(self.baseUrl + "/" + id, topic);
            };

        }]);
})();