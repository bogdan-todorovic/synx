(function() {
    angular.module("synxApp")
        .service("commentService", ["$http", function($http) {
            var self = this;
            self.baseUrl = "rest/comments";

            self.getCommentsByTopicId = function(id) {
                return $http.get(self.baseUrl + "/" + id);
            }

            self.createComment = function(data) {
                return $http.post(self.baseUrl, data);
            }
        }]);
})();