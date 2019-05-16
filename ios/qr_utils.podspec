#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html
#
Pod::Spec.new do |s|
  s.name             = 'qr_utils'
  s.version          = '0.1.0'
  s.summary          = 'A new Flutter QR scanner and generator plugin.'
  s.description      = <<-DESC
A new Flutter QR scanner and generator plugin.
                       DESC
  s.homepage         = 'https://www.aeologic.com/'
  s.license          = { :file => '../LICENSE' }
  s.author           = { 'Deepak Nishad' => 'deepak@aeologic.com' }
  s.source           = { :path => '.' }
  s.source_files = 'Classes/**/*'
  s.public_header_files = 'Classes/**/*.h'
  s.dependency 'Flutter'

  s.ios.deployment_target = '8.0'
end

