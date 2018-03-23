require 'sinatra'
require 'sinatra/reloader'

set :bind, '0.0.0.0'
set :port, 8080

get '/' do
  erb :'index.html'
end

get '/log' do
  'coucou'
end

get '/test' do
  'ceci est un test'
end

get '/hello/:name' do
  "Good morning #{params['name']}"
end