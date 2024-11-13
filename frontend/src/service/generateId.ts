export const generateShortId = () => {
    const array = new Uint8Array(6);
    crypto.getRandomValues(array);
    return Array.from(array, byte => byte.toString(36).padStart(2, '0')).join('').substring(0, 12);
}