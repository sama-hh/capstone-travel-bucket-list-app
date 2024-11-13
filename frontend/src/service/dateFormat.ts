export const timeFormat = (timestamp: string) => {
    return new Date(timestamp).toLocaleString('en-GB', {
        hour: '2-digit',
        minute: '2-digit',
        hour12: true
    });
};

export const dateFormat = (timestamp: string) => {
    return new Date(timestamp).toLocaleString('en-GB', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
    });
};
