require 'sinatra'
require 'sinatra/reloader' if development?

get '/log' do
    'coucou'
end

get '/test' do 
    'ceci est un test'
end