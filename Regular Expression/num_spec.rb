# num_spec.rb

require 'rspec'

# Regular expression for C++ integer literals
cpp_integer_regex = begin
  # Decimal literals (either '0' alone or numbers starting with [1-9])
  decimal = /0|[1-9][0-9]*/

  # Octal literals (starting with 0, followed by [0-7]+)
  octal = /0[0-7]+/

  # Hexadecimal literals (starting with 0x or 0X)
  hexadecimal = /0[xX][0-9a-fA-F]+/

  # Binary literals (starting with 0b or 0B)
  binary = /0[bB][01]+/

  # Optional suffixes: U, L, LL (with case insensitivity)
  suffix = /[uU]?[lL]{0,2}/

  # Combine all parts with the suffix
  /\A(#{decimal}|#{octal}|#{hexadecimal}|#{binary})#{suffix}\z/
end

RSpec.describe 'C++ integer literal regex' do
  it 'matches valid decimal literals' do
    expect('1234').to match(cpp_integer_regex)
    expect('0').to match(cpp_integer_regex)
    expect('987654321').to match(cpp_integer_regex)
  end

  it 'matches valid octal literals' do
    expect('01234').to match(cpp_integer_regex)
    expect('0777').to match(cpp_integer_regex)
  end

  it 'matches valid hexadecimal literals' do
    expect('0x1A3F').to match(cpp_integer_regex)
    expect('0XABCDEF').to match(cpp_integer_regex)
  end

  it 'matches valid binary literals' do
    expect('0b101010').to match(cpp_integer_regex)
    expect('0B1111').to match(cpp_integer_regex)
  end

  it 'matches literals with valid suffixes' do
    expect('1234U').to match(cpp_integer_regex)
    expect('0x1A3FL').to match(cpp_integer_regex)
    expect('0b101010LL').to match(cpp_integer_regex)
    expect('077ULL').to match(cpp_integer_regex)
  end

  it 'does not match invalid literals' do
    expect('1234.0').not_to match(cpp_integer_regex) # Decimal with a dot
    expect('0xGHI').not_to match(cpp_integer_regex)  # Invalid hex
    expect('0b102').not_to match(cpp_integer_regex)  # Invalid binary
    expect('1234LLL').not_to match(cpp_integer_regex) # Invalid suffix
  end
end
