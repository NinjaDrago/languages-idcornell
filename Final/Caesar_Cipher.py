def caesar_cipher(text, shift, mode):
    """
    Encrypts or decrypts the text based on the given mode (encrypt or decrypt).
    :param text: The text to be encrypted or decrypted.
    :param shift: The shift value for the cipher.
    :param mode: The operation mode ('encrypt' or 'decrypt').
    :return: The processed text.
    """
    result = []
    
    if mode == 'decrypt':
        shift = -shift  # Reverse the shift for decryption
    
    for char in text:
        if char.isalpha():  # Check if the character is a letter
            shift_base = 65 if char.isupper() else 97
            new_char = chr((ord(char) - shift_base + shift) % 26 + shift_base)
            result.append(new_char)
        else:
            result.append(char)  # Non-alphabetical characters are not changed
    
    return ''.join(result)

def main():
    # Ask the user for the shift value
    shift_value = int(input("Enter the shift value (e.g., 3): "))
    
    # Ask the user for the operation (encrypt or decrypt)
    choice = input("Would you like to encrypt or decrypt a message? (E/D): ").strip().lower()
    
    # Ask the user for the text (message) to process
    text = input("Enter the message: ").strip()

    # Process based on the user's choice
    if choice == 'e':
        encrypted_text = caesar_cipher(text, shift_value, 'encrypt')
        print(f"Encrypted message: {encrypted_text}")
    elif choice == 'd':
        decrypted_text = caesar_cipher(text, shift_value, 'decrypt')
        print(f"Decrypted message: {decrypted_text}")
    else:
        print("Invalid choice. Please choose 'E' for encrypt or 'D' for decrypt.")

if __name__ == "__main__":
    main()
