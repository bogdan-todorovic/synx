(function() {
    angular.module("synxApp")
        .config(["$stateProvider", function($stateProvider) {
            
            $stateProvider
                .state("topic", {
                    url: "/topic/{title}",
                    templateUrl: "topic/topic.html",
                    controller: "topicController",
                    resolve: {
                        topicPromise: function(topicService, $stateParams) {
                            return topicService.getTopic($stateParams.title);
                        },
                        commentsPromise: function(commentService, $stateParams) {
                            return commentService.getCommentsByTopicId($stateParams.title);
                        }
                    }
                });
        }]);
})();