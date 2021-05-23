% Data from http://www.uschess.org/archive/ratings/ratedist.php
DATA = [2800 1
    2700 13
    2600 66
    2500 87
    2400 133
    2300 231
    2200 691
    2100 783
    2000 1516
    1900 1907
    1800 2682
    1700 3026
    1600 3437
    1500 3582
    1400 3386
    1300 3139
    1200 3153
    1100 2973
    1000 3021 
    900 3338
    800 3520
    700 3829
    600 3946
    500 3783
    400 3544
    300 3142
    200 2547
    100 3979];

plot(DATA(:,1),DATA(:,2))


%% Gumbel Plotting
gumbel_pdf = @(x,mu,beta) 1/beta.*exp(-((x-mu)./beta + exp(-(x-mu)./beta)));
mu = 0.5;
beta = 2.0;
points = -5:0.01:15;
plot(points,gumbel_pdf(points,mu,beta));
%%
min_elo = 100;
max_elo = 3000;

ea = @(diff) (1./(1 + 10.^(diff./400)));

ea_static = ea(-100);

total_prob = 0;
for i = 5:9
total_prob = nchoosek(i,5)*ea_static^(5)*(1-ea_static)^(i-5) + total_prob;
end

