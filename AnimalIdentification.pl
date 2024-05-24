% Animal identification system in Prolog

% Facts defining the characteristics of various animals
animal(lion) :-
    has_mane,
    is_carnivore,
    has_four_legs.

animal(tiger) :-
    has_stripes,
    is_carnivore,
    has_four_legs.

animal(elephant) :-
    has_trunk,
    is_herbivore,
    has_four_legs.

animal(giraffe) :-
    has_long_neck,
    is_herbivore,
    has_four_legs.

animal(kangaroo) :-
    has_pouch,
    is_herbivore,
    has_two_legs.

% Asking questions to determine characteristics
has_mane :-
    write('Does it have a mane? (yes/no) '),
    read(yes).

has_stripes :-
    write('Does it have stripes? (yes/no) '),
    read(yes).

has_trunk :-
    write('Does it have a trunk? (yes/no) '),
    read(yes).

has_long_neck :-
    write('Does it have a long neck? (yes/no) '),
    read(yes).

has_pouch :-
    write('Does it have a pouch? (yes/no) '),
    read(yes).

is_carnivore :-
    write('Is it a carnivore? (yes/no) '),
    read(yes).

is_herbivore :-
    write('Is it a herbivore? (yes/no) '),
    read(yes).

has_four_legs :-
    write('Does it have four legs? (yes/no) '),
    read(yes).

has_two_legs :-
    write('Does it have two legs? (yes/no) '),
    read(yes).

% Starting the identification process
identify :-
    animal(Animal),
    write('The animal is a: '), write(Animal), nl.

% If no animal is identified
identify :-
    write('No matching animal found.'), nl.
