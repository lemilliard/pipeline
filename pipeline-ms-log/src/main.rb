require 'sinatra'
require 'sinatra/reloader'
require 'json'
require 'pp'

set :bind, '0.0.0.0'
set :port, 8080

get '/' do
  erb :'index.html'
end

post '/log' do
  path = 'C:/Users/ludau/GoPath/src/github.com/pipeline/pipeline-ms-log/src/log/logs.txt'
  values = JSON.parse(request.env["rack.input"].read)
  begin
    file = File.open(path, 'a')
    file.puts(values.to_json) 
  rescue IOError => e
    #some error occur, dir not writable etc.
  ensure
    file.close unless file.nil?
  end
end

get '/log' do 
    File.open('C:/Users/ludau/GoPath/src/github.com/pipeline/pipeline-ms-log/src/log/logs.txt', 'r')
end

get '/hello/:name' do
  "Good morning #{params['name']}"
end