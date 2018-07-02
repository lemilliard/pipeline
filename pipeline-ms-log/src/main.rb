require 'sinatra'
require 'sinatra/reloader'
require 'json'
require 'pp'

set :bind, '0.0.0.0'
set :port, 8090

# get '/' do
#   erb :'index.html'
# end

basePath = File.dirname(__FILE__)

post '/log' do
  path = basePath + '/log/logs.txt'
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
  File.open(basePath + '/log/logs.txt', 'r')
end

# get '/hello/:name' do
#   "Good morning #{params['name']}"
# end