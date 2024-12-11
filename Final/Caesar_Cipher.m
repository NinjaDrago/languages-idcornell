% Script to perform Caesar cipher

% Prompt the user for the mode (encode or decode)
mode = input('Enter "E" to encode or "D" to decode: ', 's');
if ~ismember(upper(mode), {'E', 'D'})
    error('Invalid mode. Please enter "E" or "D".');
end

% Prompt the user for the input text
text = input('Enter the text: ', 's');

% Prompt the user for the shift value
shift = input('Enter the shift value (integer): ');
if upper(mode) == 'D'
    shift = -shift; % Reverse the shift for decoding
end

% Initialize the result as an empty string
result = text;

for i = 1:length(text)
    char_ascii = double(text(i)); % Get ASCII value of the character
    
    % Check if the character is an uppercase letter
    if char_ascii >= 65 && char_ascii <= 90
        result(i) = char(mod(char_ascii - 65 + shift, 26) + 65);
    % Check if the character is a lowercase letter
    elseif char_ascii >= 97 && char_ascii <= 122
        result(i) = char(mod(char_ascii - 97 + shift, 26) + 97);
    else
        % Non-alphabetic characters are not encrypted
        result(i) = text(i);
    end
end

% Display the result
fprintf('Result: %s\n', result);
