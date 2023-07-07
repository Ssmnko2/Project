export const tweetsHomeSelector = state => state.home.tweets;
export const scrollDataSelector = state => state.home.endScroll;
//TweetPage
export const tweetSelector = state=> state.tweet.tweet;
export const repliesSelector =state => state.tweet.comments;


export const tweetsMainPageSelector = state => state.main.tweets;
export const dataModalMainPage = state => state.main.modalData;
export const VisibleNoAutorizateModalSelector = state => state.main.VisibleNoAutorizateModal;
export const filteredUsersSelector = state => state.chat.filteredUsers;
export const getUserChats = state => state.chat.userChats;
export const getAllMessagesLoading = state => state.chat.allMessagesLoading;
export const getAllMessagesForAllChats = state => state.chat.allMessages;
export const getActiveChat = state => state.chat.activeChat;
export const getUser = state => state.user.user;
export const getMessagesForChat = state => state.chat.chatMessages;
export const getChatOwners = state => state.chat.chatOwners;
export const isModalOpened = state => state.chat.newMessageModal;

//login
export const VisibleLoginModalSelector = state => state.main.VisibleLoginModal;
//registration
export const VisibleRegistrationModalSelector = state => state.main.VisibleRegistrationModal;
export const VisibleNextRegistrationModalSelector = state => state.main.VisibleNextRegistrationModal;
//error
export const loginErrorSelector = state => state.user.loginError;
export const registrationErrorSelector = state => state.registration.registrationError;
//QuoteRetweet
export const tweetInQuoteModalSelector = state => state.quoteRetweet.parentTweet;
export const visibleQuoteModalSelector = state => state.quoteRetweet.visibleQuoteRetweetModal;
export const textQuoteModalSelector = state => state.quoteRetweet.quoteRetweetData.text;
export const  imageQuoteModalSelector = state => state.quoteRetweet.quoteRetweetData.img;
//Reply
export const tweetInReplySelector = state => state.reply.parentTweet;
export const visibleReplyModalSelector = state => state.reply.visibleReplyModal;
export const textReplySelector = state => state.reply.replyData.text;
export const  imageReplySelector = state => state.reply.replyData.img;
export const currentUserIdSelector = state => state.user.user.id;
//Modal
export const visibleImageModalSelector = state => state.main.visibleImageModal;
export const visibleForgotModalSelector = state => state.main.visibleForgotModal;
//Bookmark
export const bookmarkDataSelector = state => state.bookmark.bookmarks;
//
export const notificationsDataSelector = state => state.notifications.notifications;


