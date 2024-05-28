movie(conjuring,hollywood,english,hotstar,horror).
movie(bhool_Bhulaiyaa2,bollywood,hindi,netflix,comedy).
movie(twelve_Fail,bollywood,hindi,hotstar,motivational).
movie(msdhoni,bollywood,hindi,amazonprime,motivational).
movie(golmaal,bollywood,hindi,hotstar,comedy).
movie(kung_Fu_Panda,hollywood,english,netflix,comedy).
movie(shershaah,bollywood,hindi,netflix,patriotic).
movie(fighter,bollywood,hindi,hotstar,patriotic).

identify_movie(Type,Language,Platform,Theme,Movie):-movie(Movie,Type,Language,Platform,Theme).
%the comma (,) is used as a conjunction operator,nl as newline character
% ; else part
% atoms should be lowercase in start

start:-
    write('Welcome to Movie Suggestion System'),nl,
    write('We will help you find a movie'),nl,
    write('What type of movie you want to watch(hollywood,bollywood)?'),read(Type),nl,
    write('What is preferable language of movie(english/hindi)?'),read(Language),nl,
    write('What is  your preferred platform(netflix,hotstar,amazonprime)?'),read(Platform),nl,
    write('What should be the theme of a movie(horror,comedy,motivational,patriotic)?'),read(Theme),nl,
    identify_movie(Type,Language,Platform,Theme,Movie),
    (
        Movie\=[]->write('Based on your description,the movie should be '),write(Movie),nl
        ;write('No movie matching your preference'),nl
    ).

