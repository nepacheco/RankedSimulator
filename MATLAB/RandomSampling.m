%% Gaussian Distribution
sigma = 1;
mu = 0;

pdf = @(x,mu,sigma) 1/(sigma*sqrt(2*pi))*exp(-(x-mu)^2/(2*sigma^2));


num_samples = 100000;
samples = zeros(1,num_samples);
% for i = 1:n
%     sample(1,i) = rand()
% end


%% Gumbel

% mu = 894.1510368;
% beta =  275.9377;
% mu = 826607021078931725/830626025293671;
% beta = 1808476725365964800/5814382177055697;
% mu = 8423446256269630/8064330342657;
% beta = 2814749767106560/8064330342657;
max_rating = 2900;
min_rating = 100;
range = min_rating:.1:max_rating;
max_prob = 0.999999981000000;
min_prob = 1.900000001775482e-08;
interval_prob = 0.01;
prob_range = min_prob:interval_prob:max_prob;

beta = (max_rating*2.5 - min_rating/2.5)/(log(-log(min_prob)) - log(-log(max_prob)));
mu = min_rating/2.5 + beta*log(-log(min_prob));


F = @(x) exp(-exp(-(x-mu)./beta));
f = @(x) exp(-((x-mu)/beta + exp(-(x-mu)/beta)))*1/beta;
x = @(P) -beta.*log(-log(P)) + mu;

% figure 
% hold on
% plot(range,F(range),'LineWidth',2)
num_sims = 1000000;
pairs = zeros(num_sims,2);
counter = 1;
while counter <= num_sims
    y = rand();
    x_temp = x(y);
    if x_temp > max_rating
        continue
    elseif x_temp < min_rating
        continue
    end
    pairs(counter,:) = [x_temp,y];
    counter = counter + 1;
end
% plot(x(prob_range),prob_range,'r--','LineWidth',3)
% figure
% plot(range,f(range),'r-','LineWidth',4)
hold on
histogram(pairs(:,1),20,'Normalization','probability');

%% Gumbel mu and beta calculations
max_rating = 2900;
min_rating = 100;
perc_pop = 0.999999962;
left_out = 1-perc_pop;
P_min = left_out/2;
P_max = (1-left_out/2);


syms beta mu
f1 = -beta*log(-log(P_max)) + mu == max_rating*2.5;
f2 = -beta*log(-log(P_min)) + mu == min_rating/2.5;
solution = solve(f1,f2);
solution.beta;
solution.mu;
% beta = 135.5484
% mu = 490.1093


%% Logistic Distribution
clc; clear; close all
mu = 1200;
beta = 200;
range = 100:.1:2900;
max_prob = 0.99999;
min_prob = 0.0001;
interval_prob = 0.01;
prob_range = min_prob:interval_prob:max_prob;

F = @(x) 1./(1 + exp(-(x-mu)./beta));
f = @(x) exp(-(x-mu)/beta)./(beta*(1 + exp(-(x-mu)./beta)).^2);
x = @(P) -beta.*log(P/(1-P)) + mu;

figure 
hold on
plot(range,F(range),'LineWidth',2)
num_sims = 1000000;
pairs = zeros(num_sims,2);
for i = 1:num_sims
    y = rand();
    if y > max_prob
        y = max_prob;
    elseif y < min_prob
        y = min_prob;
    end
    pairs(i,:) = [x(y),y];
end
plot(x(prob_range),prob_range,'r--','LineWidth',3)
figure
plot(range,f(range),'r-','LineWidth',2)
hold on
% histogram(pairs(:,1),1000,'Normalization','pdf');

