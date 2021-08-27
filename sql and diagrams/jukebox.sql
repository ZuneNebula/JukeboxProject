drop database jukebox;
create database jukebox;
use jukebox;


/*TABLES*/
create table playlistEntry(
sno int auto_increment not null,
name varchar(50) not null,
artist varchar(30) not null,
duration time not null,
type varchar(15) not null,
primary key(sno)
);

create table songs(
sno int auto_increment not null,
entryNo int not null,
album varchar(50) not null,
genre varchar(30) not null,
primary key (sno),
unique(entryNo),
foreign key(entryNo) references playlistEntry(sno)
);

create table podcasts(
sno int auto_increment not null,
entryNo int not null,
date date not null,
primary key (sno),
unique(entryNo),
foreign key(entryNo) references playlistEntry(sno)
);

create table users(
sno int auto_increment not null,
name varchar(50) not null,
primary key(sno)
);

create table playlist(
sno int auto_increment not null,
entryNo int not null,
name varchar(30) not null,
userNo int not null,
primary key(sno),
foreign key(userno) references users(sno),
foreign key (entryNo) references playlistEntry(sno)
);

/*SONG QUERIES*/
select songs.sno, playlistentry.name, playlistentry.artist, songs.album, songs.genre, playlistentry.duration
from playlistentry join songs on playlistentry.sno = songs.entryno;

select songs.sno, playlistentry.name, playlistentry.artist, songs.album, songs.genre, playlistentry.duration
from playlistentry join songs on playlistentry.sno = songs.entryno
where playlistentry.artist = 'John';

/*PODCAST QUERIES*/
select podcasts.sno, playlistentry.name, playlistentry.artist, podcasts.date, playlistentry.duration
from playlistentry join podcasts on playlistentry.sno = podcasts.entryno;

select podcasts.sno, playlistentry.name, playlistentry.artist, podcasts.date, playlistentry.duration
from playlistentry join podcasts on playlistentry.sno = podcasts.entryno
where playlistentry.artist = 'John';

/*PLAYLIST QUERIES*/
select playlistentry.name,playlistentry.artist,playlistentry.duration,playlistentry.type 
from playlistentry join playlist on playlistentry.sno = playlist.entryno
where playlist.name = 'evening music';

insert into playlist values(default,1,'evening workout', 1);

/*USER QUERIES*/
insert into users values(default,'mihir');

/*DB SETUP*/
select * from playlistentry;
drop table playlistentry;

insert into playlistentry values(default,'Bay Street Billionaires', 'Squadda B', '0:2:59','Song');
insert into playlistentry values(default,'Space Age Hussle', 'Squadda B', '0:1:49','Song');
insert into playlistentry values(default,'Early Anvil', 'Unicorn Heads', '0:8:13','Song');
insert into playlistentry values(default,'Tropical Thunder', 'RKVC', '0:2:28','Song');
insert into playlistentry values(default,'Time to Spare', 'An Joe', '0:2:14','Song');
insert into playlistentry values(default,'Toddler Brand', 'Godmode', '0:1:48','Song');
insert into playlistentry values(default,'Emergency on level 3', 'Jeremy Konpas', '0:3:46','Song');

insert into playlistentry values(default,'Sunset Dream', 'Cheel', '0:2:42','Podcast');
insert into playlistentry values(default,'Know Myself', 'Patrick Patrikios', '0:3:23','Podcast');
insert into playlistentry values(default,'New Day', 'Patrick Patrikios', '0:2:15','Podcast');
insert into playlistentry values(default,'Blank Slate', 'VYEN', '0:1:59','Podcast');
insert into playlistentry values(default,'Cruise','John Delay','0:0:06','Podcast');

insert into songs values(default,1,'Bb','Hip Hop');
insert into songs values(default,2,'Bb','Hip Hop');
insert into songs values(default,3,'EA','Ambient');
insert into songs values(default,4,'Angr','Rock');
insert into songs values(default,5,'Rel','Ambient');
insert into songs values(default,6,'Roc','Rock');
insert into songs values(default,7,'Suspense','Rock');
select * from songs;

insert into podcasts values(default,8,'2020-12-5');
insert into podcasts values(default,9,'2021-6-20');
insert into podcasts values(default,10,'2021-5-3');
insert into podcasts values(default,11,'2020-8-20');
insert into podcasts values(default,12,'2021-08-27');
select * from podcasts;

insert into users values(default,'mihir');
insert into users values(default,'sharma');
select * from users;

insert into playlist values(default,5,'evening music',1);
insert into playlist values(default,6,'evening music',1);
insert into playlist values(default,7,'evening music',1);
insert into playlist values(default,1,'morning music',1);
insert into playlist values(default,2,'morning music',1);
insert into playlist values(default,3,'morning music',1);
insert into playlist values(default,4,'night music',2);
insert into playlist values(default,8,'night music',2);
insert into playlist values(default,9,'night music',2);
insert into playlist values(default,1,'night music',2);

select name from playlist where userno = 1 group by name;

select playlistentry.sno, playlistentry.name,playlistentry.artist,playlistentry.duration,playlistentry.type
from playlistentry join playlist on playlistentry.sno = playlist.entryno
where playlist.name = 'evening music';

select songs.album, songs.genre from songs
join playlistentry on songs.entryno = playlistentry.sno
where playlistentry.sno = 1;

select podcasts.date from podcasts
join playlistentry on podcasts.entryno = playlistentry.sno
where playlistentry.sno = 8;

select * from users;

select * from playlistentry;

insert into playlist values(default,4,'dusk',1);

select entryno from playlist where name ='dusk' and userno = 3;

select * from playlist where name = 'dawn';

select * from playlistentry;

select * from songs;

select * from playlist;

